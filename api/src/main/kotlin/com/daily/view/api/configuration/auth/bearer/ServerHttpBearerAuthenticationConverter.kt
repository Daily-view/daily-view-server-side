package com.daily.view.api.configuration.auth.bearer

import com.daily.view.api.configuration.auth.jwt.JWTCustomVerifier
import com.daily.view.api.configuration.auth.jwt.create
import com.daily.view.api.configuration.auth.jwt.extract
import com.nimbusds.jose.crypto.MACVerifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Function
import java.util.function.Predicate

class ServerHttpBearerAuthenticationConverter(
    @Value("\${jwt.secret}")
    val jwtSecret: String,
) : Function<ServerWebExchange?, Mono<Authentication>> {
    private val jwtVerifier: JWTCustomVerifier = JWTCustomVerifier(this.buildJWSVerifier(), jwtSecret)

    override fun apply(serverWebExchange: ServerWebExchange?): Mono<Authentication> {
        return Mono.justOrEmpty(serverWebExchange)
            .flatMap { extract(serverWebExchange!!) }
            .filter(matchBearerLength)
            .flatMap(isolateBearerValue)
            .flatMap(jwtVerifier::check)
            .flatMap { create(it) }.log()
    }

    private fun buildJWSVerifier(): MACVerifier = MACVerifier(jwtSecret)

    companion object {
        private const val BEARER = "Bearer "
        private val matchBearerLength = Predicate { authValue: String -> authValue.length > BEARER.length }
        private val isolateBearerValue =
            Function { authValue: String -> Mono.justOrEmpty(authValue.substring(BEARER.length)) }
    }
}
