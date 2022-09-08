package com.daily.view.api.service.member

import com.daily.view.api.exception.BusinessException
import com.daily.view.api.exception.ErrorCode
import com.daily.view.domain.entity.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(private val memberRepository: MemberRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByEmail(username)
            ?: throw BusinessException(ErrorCode.MEMBER_DOES_NOT_EXISTS, "이메일을 확인해주세요.")
        return UserDetailsImpl(member)
    }
}
