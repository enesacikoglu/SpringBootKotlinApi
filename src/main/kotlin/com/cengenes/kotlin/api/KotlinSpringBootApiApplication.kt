package com.cengenes.kotlin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootApiApplication

object KotlinMain {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<KotlinSpringBootApiApplication>(*args)
    }
}