package com.daily.view.api.configuration.auth.jwt

import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jwt.SignedJWT
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.Date
import java.util.function.Predicate

class JWTCustomVerifier(
    private val jwsVerifier: JWSVerifier,
    val jwtSecret: String,
) {

    fun check(token: String): Mono<SignedJWT> {
        return Mono.justOrEmpty(createJws(token))
            .filter(isNotExpired())
            .filter(validSignature())
    }

    private fun validSignature(): Predicate<SignedJWT> =
        Predicate { token: SignedJWT -> token.verify(jwsVerifier) }

    private fun isNotExpired(): Predicate<SignedJWT> =
        Predicate { token: SignedJWT -> getExpirationDate(token).after(Date.from(Instant.now())) }

    private fun createJws(token: String): SignedJWT = SignedJWT.parse(token)

    private fun getExpirationDate(token: SignedJWT): Date = token.jwtClaimsSet.expirationTime
}
