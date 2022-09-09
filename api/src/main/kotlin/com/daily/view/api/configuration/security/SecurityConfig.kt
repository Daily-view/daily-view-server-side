package com.daily.view.api.configuration.security

import com.daily.view.api.configuration.jwt.JwtAuthenticationConverter
import com.daily.view.api.configuration.jwt.JwtAuthenticationManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.core.userdetails.User

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        authManger: JwtAuthenticationManager,
        converter: JwtAuthenticationConverter
    ): SecurityWebFilterChain {
        val filter = AuthenticationWebFilter(authManger)
        filter.setServerAuthenticationConverter(converter)

        return http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .csrf().disable()
            .logout().disable()
            .authorizeExchange()
            .pathMatchers("/**").permitAll()
            .and()
            .addFilterAfter(filter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange().anyExchange().authenticated().and()
            .build()
    }

}
