package com.daily.view.api.dto.member

import java.time.OffsetDateTime
import java.util.LongSummaryStatistics

data class MemberDto(
    val id: Long,
    val nickname: String,
    val email: String,
    val lastLoginAt: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)