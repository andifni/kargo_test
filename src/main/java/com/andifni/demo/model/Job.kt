package com.andifni.demo.model

import java.util.*

data class Job (
        val id: String?=null,
        val budget: Double?=0.0,
        val origin: String?=null,
        val destination: String?=null,
        val shipmentDate: Date? = Date(),
        val desc: String?=null,
        val shipperId: String? = null
)