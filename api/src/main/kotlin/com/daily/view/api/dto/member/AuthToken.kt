package com.daily.view.api.dto.member

import kotlin.String

data class AuthToken(
    val token_type: String,
    val token: String
)
