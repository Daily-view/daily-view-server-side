package com.daily.view.domain.entity.redis

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit
import javax.persistence.Id

@RedisHash("refresh_tokens")
class MemberRefreshToken(
    @Indexed
    var tokenValue: String,
    @Indexed
    val memberId: Long,
    @TimeToLive(unit = TimeUnit.DAYS)
    var timeout: Long = 7L,
    var createdAt: OffsetDateTime = OffsetDateTime.now()
) {
    @Id
    @Indexed
    var id: String? = null

    fun updateToken(newTokenValue: String) {
        this.tokenValue = newTokenValue
    }
}
