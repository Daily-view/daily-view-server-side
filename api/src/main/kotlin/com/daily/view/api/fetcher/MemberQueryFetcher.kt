package com.daily.view.api.fetcher

import com.daily.view.api.dto.CreateMemberInput
import com.daily.view.api.service.member.MemberService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class MemberMutationFetcher(
    private val memberService: MemberService
) {

    @MutationMapping(value = "createMember")
    fun signup(@Argument input: CreateMemberInput): Mono<Boolean> {
        return memberService.create(input)
    }
}
