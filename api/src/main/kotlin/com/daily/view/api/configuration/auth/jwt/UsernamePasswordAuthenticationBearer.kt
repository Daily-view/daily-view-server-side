package com.daily.view.api.configuration.auth.jwt

import com.nimbusds.jwt.SignedJWT
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import reactor.core.publisher.Mono
import java.text.ParseException

fun create(signedJWTMono: SignedJWT): Mono<Authentication> {
    val subject: String
    val auths: String
    val authorities: List<*>
    try {
        subject = signedJWTMono.jwtClaimsSet.subject
        auths = signedJWTMono.jwtClaimsSet.getClaim("roles") as String
    } catch (e: ParseException) {
        return Mono.empty()
    }
    authorities = listOf(*auths.run { split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() })
        .map { a: String? -> SimpleGrantedAuthority(a) }
    return Mono.justOrEmpty(UsernamePasswordAuthenticationToken(subject, null, authorities))
}
