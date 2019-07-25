package com.andifni.demo.controllers

import com.andifni.demo.model.Bid
import com.andifni.demo.model.Job
import com.andifni.demo.utils.MergeSort
import com.andifni.demo.utils.bidArrayParser
import com.andifni.demo.utils.jobArrayParser
import com.andifni.demo.utils.jsonReader
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @GetMapping("/bid")
    fun getBid(@RequestParam(value="by", required = false, defaultValue = "price") orderBy: String,
                     @RequestParam(value="asc", required = false, defaultValue = "1") asc: String
                     ): List<Bid> {

        val isAsc = asc == "1"
        val comparator = Comparator<Bid> { o1, o2 ->
            val multiplier = if (isAsc) 1 else -1
            return@Comparator when (orderBy) {
                "price" -> (o1.price!!.compareTo(o2.price!!)) * multiplier
                "vehicle" -> (o1.vehicle!!.compareTo(o2.vehicle!!)) * multiplier
                else -> (o1.price!!.compareTo(o2.price!!))  * multiplier
            }
        }

        val jsonObjectBid = jsonReader("bid.json")
        val jsonArray = jsonObjectBid.getJSONArray("bids")
        val list = bidArrayParser(jsonArray)

        val sorter = MergeSort(list, comparator)

        return sorter.sort()
    }

    @GetMapping("/job")
    fun getJob(@RequestParam(value="by", required = false, defaultValue = "origin") orderBy: String,
               @RequestParam(value="asc", required = false, defaultValue = "1") asc: String
    ): List<Job> {

        val isAsc = asc == "1"
        val comparator = Comparator<Job> { o1, o2 ->
            val multiplier = if (isAsc) 1 else -1
            return@Comparator when (orderBy) {
                "origin" -> (o1.origin!!.compareTo(o2.origin!!)) * multiplier
                "destination" -> (o1.destination!!.compareTo(o2.destination!!)) * multiplier
                "budget" -> (o1.budget!!.compareTo(o2.budget!!))* multiplier
                "shipmentDate" -> (o1.shipmentDate!!.compareTo(o2.shipmentDate!!))* multiplier
                else -> (o1.origin!!.compareTo(o2.origin!!)) * multiplier
            }
        }

        val jsonObjectBid = jsonReader("job.json")
        val jsonArray = jsonObjectBid.getJSONArray("jobs")
        val list = jobArrayParser(jsonArray)

        val sorter = MergeSort(list, comparator)

        return sorter.sort()
    }


}