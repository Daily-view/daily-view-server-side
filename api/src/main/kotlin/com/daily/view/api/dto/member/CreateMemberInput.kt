package com.daily.view.api.dto.member

data class CreateMemberInput(
    val email: String,
    val nickname: String,
    val password: String,
)
