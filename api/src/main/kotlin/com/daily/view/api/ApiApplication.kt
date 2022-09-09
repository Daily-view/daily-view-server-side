package com.daily.view.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
