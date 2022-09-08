package com.daily.view.api.configuration.auth.jwt

import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.security.core.GrantedAuthority
import java.time.Period
import java.util.Date
import java.util.stream.Collectors

fun generateToken(subject: String?, credentials: Any?, authorities: Collection<GrantedAuthority?>): String {
    val signedJWT: SignedJWT

    val claimsSet: JWTClaimsSet = JWTClaimsSet.Builder()
        .subject(subject)
        .issuer("jongyun.ha")
        .expirationTime(Date(expiration()))
        .claim(
            "roles",
            authorities
                .stream()
                .map { obj: Any? -> GrantedAuthority::class.java.cast(obj) }
                .map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.joining(","))
        )
        .build()
    signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimsSet)
    try {
        signedJWT.sign(JWTCustomSigner.signer)
    } catch (e: JOSEException) {
        e.printStackTrace()
    }
    return signedJWT.serialize()
}

fun expiration(): Long = Date().toInstant().plus(Period.ofDays(1)).toEpochMilli()
