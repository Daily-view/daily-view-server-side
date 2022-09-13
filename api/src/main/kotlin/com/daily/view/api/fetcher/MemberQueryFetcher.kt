package com.daily.view.api.fetcher

import com.daily.view.api.service.member.MemberService
import com.daily.view.api.toDto
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.security.Principal

@Controller
class MemberQueryFetcher(
    private val memberService: MemberService
) {
    @QueryMapping
    fun me(principal: Principal) = memberService.findByPrincipal(principal).toDto()
}
