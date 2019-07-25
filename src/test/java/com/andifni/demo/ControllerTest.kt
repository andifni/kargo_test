package com.andifni.demo

import junit.framework.Assert.assertEquals
import org.json.JSONArray
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `test call bid`() {
        val result = testRestTemplate.getForEntity("/bid", String::class.java)
        assertEquals(result.statusCode, HttpStatus.OK)
        val body = result.body
        val jsonArray = JSONArray(body)
        assertEquals(jsonArray.length(), 9)
    }

    @Test
    fun `test call job`() {
        val result = testRestTemplate.getForEntity("/job", String::class.java)
        assertEquals(result.statusCode, HttpStatus.OK)
        val body = result.body
        val jsonArray = JSONArray(body)
        assertEquals(jsonArray.length(), 8)
    }

    @Test
    fun `test call bid order by vehicle`() {
        val result = testRestTemplate.getForEntity("/bid?by=vehicle", String::class.java)
        assertEquals(result.statusCode, HttpStatus.OK)
        val body = result.body
        val jsonArray = JSONArray(body)
        assertEquals(jsonArray.length(), 9)
        val first = jsonArray.getJSONObject(0)
        val last = jsonArray.getJSONObject(8)
        val vehicle1 = first.getString("vehicle")
        val vehicle2 = last.getString("vehicle")
        assertEquals(vehicle2, "tempor")
        assertEquals(vehicle1, "culpa")
    }

    @Test
    fun `test call bid order by vehicle descending`() {
        val result = testRestTemplate.getForEntity("/bid?by=vehicle&asc=0", String::class.java)
        assertEquals(result.statusCode, HttpStatus.OK)
        val body = result.body
        val jsonArray = JSONArray(body)
        assertEquals(jsonArray.length(), 9)
        val first = jsonArray.getJSONObject(0)
        val last = jsonArray.getJSONObject(8)
        val vehicle1 = first.getString("vehicle")
        val vehicle2 = last.getString("vehicle")
        assertEquals(vehicle1, "tempor")
        assertEquals(vehicle2, "culpa")
    }


}