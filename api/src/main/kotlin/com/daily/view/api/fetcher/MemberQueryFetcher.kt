package com.daily.view.api.fetcher

import com.daily.view.api.service.member.MemberService
import org.springframework.stereotype.Controller

@Controller
class MemberQueryFetcher(
    private val memberService: MemberService
)
