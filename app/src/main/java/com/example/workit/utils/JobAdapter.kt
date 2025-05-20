package com.example.workit.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.R
import com.example.workit.model.JobItem

class JobAdapter(private var jobList: List<JobItem> = emptyList()) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.company_logo)
        val jobTitleView: TextView = itemView.findViewById(R.id.job_title)
        val companyNameView: TextView = itemView.findViewById(R.id.company_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_job, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jobList[position]
        holder.jobTitleView.text = item.job_name
        holder.companyNameView.text = item.company_name
        holder.imageView.setImageResource(item.logo)
    }

    override fun getItemCount(): Int = jobList.size

    // Metode untuk memperbarui data
    fun updateData(newJobList: List<JobItem>) {
        jobList = newJobList
        notifyDataSetChanged()
    }
}