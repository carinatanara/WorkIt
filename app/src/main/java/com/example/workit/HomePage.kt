package com.example.workit

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.databinding.ActivityHomePageBinding
import com.example.workit.model.CompanyItem
import com.example.workit.model.JobItem
import com.example.workit.utils.CompanyAdapter
import com.example.workit.utils.JobAdapter
import com.google.android.material.tabs.TabLayout

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var jobAdapter: JobAdapter  // Adapter untuk Job List
    private lateinit var companyAdapter: CompanyAdapter  // Adapter untuk Company List
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvListjob

        val rvJobs = binding.rvListjob
        val rvCompanies = binding.rvListcompany

        rvJobs.layoutManager = LinearLayoutManager(this)
        rvCompanies.layoutManager = LinearLayoutManager(this)

        jobAdapter = JobAdapter(getJobList())
        companyAdapter = CompanyAdapter(getCompanyList())

        rvJobs.adapter = jobAdapter
        rvCompanies.adapter = companyAdapter


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_frag)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val tabs: TabLayout = binding.tabs

        val btnPartTime = binding.jobFilter.findViewById<Button>(R.id.btn_part_time)
        val btnIntern = binding.jobFilter.findViewById<Button>(R.id.btn_intern)
        val btnFulltime = binding.jobFilter.findViewById<Button>(R.id.btn_fulltime)

        // Optional: Add tab selection listener
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Handle tab selection
                when (tab.position) {
                    0 -> {
                        rvJobs.visibility = View.VISIBLE
                        rvCompanies.visibility = View.GONE
                        binding.search.queryHint = "Search jobs..."
                        binding.jobFilter.visibility = View.VISIBLE
                        binding.companyFilter.visibility = View.GONE
                    }

                    1 -> {
                        rvJobs.visibility = View.GONE
                        rvCompanies.visibility = View.VISIBLE
                        binding.search.queryHint = "Search companies..."
                        binding.jobFilter.visibility = View.GONE
                        binding.companyFilter.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Handle tab unselection if needed
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Handle tab reselection if needed
            }
        })

        tabs.getTabAt(1)?.select()

        btnPartTime.setOnClickListener {
            val filteredJobs = getJobList().filter { it.category == "Part-time" }
            jobAdapter.updateData(filteredJobs)
        }

    }

    private fun getJobList(): List<JobItem> {
        // Return daftar pekerjaan
        return listOf(
            JobItem("UI/UX Designer", "BCA", R.drawable.bca_logo, "Intern"),
            JobItem("Software Engineer", "Shopee", R.drawable.bca_logo, "Intern"),
            JobItem("Bank Manager", "OCBC Bank", R.drawable.bca_logo, "Fulltime"),
            JobItem("Barista", "Starbucks", R.drawable.bca_logo, "Part-time")
        )
    }

    private fun getCompanyList(): List<CompanyItem> {
        // Return daftar perusahaan
        return listOf(
            CompanyItem("BCA", "Banking", R.drawable.bca_logo),
            CompanyItem("Shopee", "E-commerce", R.drawable.bca_logo),
            CompanyItem("OCBC Bank", "Banking", R.drawable.bca_logo),
            CompanyItem("Starbucks", "Food & Beverage", R.drawable.bca_logo)
        )
    }

}