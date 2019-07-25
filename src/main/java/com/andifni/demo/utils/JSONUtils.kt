package com.andifni.demo.utils

import com.andifni.demo.model.Bid
import com.andifni.demo.model.Job
import jdk.nashorn.internal.parser.JSONParser
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.util.ResourceUtils
import java.io.FileReader
import java.text.SimpleDateFormat

fun jsonReader(path: String): JSONObject {

    val file = ResourceUtils.getFile("classpath:$path")
    val fileReader = FileReader(file)
    val string = fileReader.readText()
    return JSONObject(string)
}

fun bidParser(jsonObject: JSONObject): Bid {
    return Bid(
            id = jsonObject.optString("id"),
            price = jsonObject.optDouble("price"),
            note = jsonObject.optString("note"),
            jobId = jsonObject.optString("jobId"),
            transporterId = jsonObject.optString("transporterId"),
            vehicle = jsonObject.optString("vehicle")
    )
}

//"id": "5d394dfc64b750729ae55072",
//"price": 3816.4,
//"note": "Esse nisi duis tempor id voluptate eiusmod mollit. Commodo ex consequat dolore anim veniam velit. In minim ea consequat elit id laboris dolor occaecat minim fugiat in enim. Fugiat culpa enim qui dolor minim mollit occaecat nulla.\r\n",
//"jobId": "5d394dfcc6371c88b643cf85",
//"transporterId": "5d394dfc15344ac09dbc806d",
//"vehicle": "fugiat"

fun jobParser(jsonObject: JSONObject): Job {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return Job(
            id = jsonObject.optString("id"),
            origin = jsonObject.optString("origin"),
            destination = jsonObject.optString("destination"),
            budget = jsonObject.optDouble("budget"),
            shipmentDate = dateFormat.parse(jsonObject.optString("shipmentDate")),
            desc = jsonObject.optString("desc"),
            shipperId = jsonObject.optString("shipperId")
            )
}

//"id": "5d394d470b36c51ccf627f24",
//"origin": "Warren",
//"destination": "Bannock, Oklahoma, 1771",
//"budget": 3514,
//"shipmentDate": "2018-11-11",
//"desc": "Esse proident aliqua tempor ullamco enim do est non. Id dolor ex ea reprehenderit deserunt aliqua magna anim cillum. Duis culpa duis eiusmod enim ex duis adipisicing quis. Sint velit amet reprehenderit esse.\r\n",
//"shipperId": "5d394d472cd96bfad7ce5824"

fun jobArrayParser(jsonArray: JSONArray): ArrayList<Job> {
    val array = ArrayList<Job>()
    for (i in 0 until jsonArray.length()) {
        array.add(jobParser(jsonArray.getJSONObject(i)))
    }
    return array
}

fun bidArrayParser(jsonArray: JSONArray): ArrayList<Bid> {
    val array = ArrayList<Bid>()
    for (i in 0 until jsonArray.length()) {
        array.add(bidParser(jsonArray.getJSONObject(i)))
    }
    return array
}