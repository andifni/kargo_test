package com.andifni.demo.model

data class Bid(
        val id: String?=null,
        val price: Double?=0.0,
        val note: String?=null,
        val jobId: String? = null,
        val vehicle: String? = null,
        val transporterId: String? = null
)