package com.andifni.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringApplication

fun main(args: Array<String>) {
    runApplication<SpringApplication>(*args)
}
