package com.daily.view.api.configuration.service.auth

data class JwtDto(val tokenType: String = "Bearer", val token: String, val refreshToken: String)
