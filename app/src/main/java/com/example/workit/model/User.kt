package com.example.workit.model

import java.util.Date

data class User (
    var id: Int, // Sequence (1..2..3..N)
    var name: String,
    var email : String,
    var password: String
//    var memberType: String, // Free, Premium, Pro
//    var joinDate : Date? = null
)