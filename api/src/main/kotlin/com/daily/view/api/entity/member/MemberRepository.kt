package com.daily.view.api.entity.member

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface MemberRepository : ReactiveCrudRepository<Members, Long> {

    fun existsByEmail(email: String): Mono<Boolean>

    fun existsByNickname(nickname: String): Mono<Boolean>

    fun findByEmail(email: String): Mono<Members>
}
