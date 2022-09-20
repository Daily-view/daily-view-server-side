package com.daily.view.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
