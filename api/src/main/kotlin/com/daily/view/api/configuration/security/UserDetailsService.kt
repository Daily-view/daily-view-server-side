package com.daily.view.api.configuration.security

import com.daily.view.api.entity.member.MemberRepository
import com.daily.view.api.service.auth.UserDetailsImpl
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsService(
    private val memberRepository: MemberRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails> {
        if (username == null) return Mono.empty()
        val member = memberRepository.findByEmail(username) ?: return Mono.empty()
        return Mono.just(UserDetailsImpl(member))
    }
}
