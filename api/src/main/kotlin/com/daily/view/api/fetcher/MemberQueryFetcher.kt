package com.daily.view.api.fetcher

import com.daily.view.api.entity.member.Members
import com.daily.view.api.service.member.MemberService
import com.daily.view.api.toDto
import java.security.Principal
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller

@Controller
class MemberQueryFetcher(
    private val memberService: MemberService
) {
    @Secured("ROLE_ADMIN")
    @QueryMapping
    fun me(principal: Principal) = memberService.findByPrincipal(principal).toDto()
}
