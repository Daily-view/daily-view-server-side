package com.daily.view.api.configuration.auth.jwt

import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.KeyLengthException
import com.nimbusds.jose.crypto.MACSigner
import org.springframework.beans.factory.annotation.Value

/**
 * Creates a JWTSigner using a simple secret string
 */
class JWTCustomSigner(
    @Value("\${jwt.secret}")
    val jwtSecret: String,
) {
    companion object {
        var signer: JWSSigner? = null
    }

    init {
        signer = try {
            MACSigner(jwtSecret)
        } catch (e: KeyLengthException) {
            null
        }
    }
}
