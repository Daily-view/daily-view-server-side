package com.daily.view.api.service.member

import com.daily.view.api.configuration.jwt.JwtSupport
import com.daily.view.api.dto.member.CreateMemberInput
import com.daily.view.api.dto.member.LoginInput
import com.daily.view.api.entity.member.MemberRepository
import com.daily.view.api.entity.member.Members
import com.daily.view.api.exception.BusinessException
import com.daily.view.api.exception.ErrorCode
import com.daily.view.api.service.auth.JwtDto
import com.daily.view.api.service.auth.RefreshTokenService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val encoder: BCryptPasswordEncoder,
    private val refreshTokenService: RefreshTokenService,
    private val jwtSupport: JwtSupport
) {

    @Transactional(readOnly = true)
    fun login(input: LoginInput): Mono<JwtDto> {
        val member = memberRepository.findByEmail(input.email)
        member?.let {
            if (encoder.matches(input.password, it.password)) {
                val bearerToken = jwtSupport.generate(input.email)
                return Mono.just(JwtDto(tokenType = "Bearer", token = bearerToken.value, refreshToken = "1234"))
            }
        }
        throw BusinessException(ErrorCode.CHECK_YOUR_ACCOUNT, "계정을 확인해주세요")
    }

    @Transactional
    fun create(input: CreateMemberInput): Mono<Boolean> {
        if (memberRepository.existsByEmail(input.email)) {
            throw BusinessException(ErrorCode.EMAIL_IS_DUPLICATED, "이메일이 중복입니다. email: ${input.email}")
        }
        if (memberRepository.existsByNickname(input.nickname)) {
            throw BusinessException(ErrorCode.NICKNAME_IS_DUPLICATED, "닉네임이 중복입니다. nickname: ${input.nickname}")
        }
        val member = Members(email = input.email, nickname = input.nickname, password = input.password)
        encoder.encode(member.password).also { member.password = it }
        memberRepository.save(member)
        return Mono.just(true)
    }
}
