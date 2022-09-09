package com.daily.view.api.dto

data class CreateMemberInput(
    val email: String,
    val nickname: String,
    val password: String,
)
