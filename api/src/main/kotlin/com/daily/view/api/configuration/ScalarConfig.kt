package com.daily.view.api.configuration

import graphql.scalars.ExtendedScalars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class ScalarConfig {

    @Bean
    fun configure(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { c ->
            c
                .scalar(ExtendedScalars.GraphQLLong)
                .scalar(ExtendedScalars.GraphQLByte)
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.GraphQLBigInteger)
                .scalar(ExtendedScalars.GraphQLShort)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.LocalTime)
                .scalar(ExtendedScalars.Object)
        }
    }
}
