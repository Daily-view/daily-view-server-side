package com.daily.view.api.fetcher

import com.daily.view.api.dto.member.AuthToken
import com.daily.view.api.dto.member.CreateMemberInput
import com.daily.view.api.dto.member.LoginInput
import com.daily.view.api.service.member.MemberService
import graphql.GraphQLContext
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

    @MutationMapping
    fun login(
        @Argument input: LoginInput,
        context: GraphQLContext
    ): Mono<AuthToken> {
        return memberService.login(input)
            .flatMap {
                context.put("token", it.token)
                Mono.just(AuthToken(it.tokenType, it.token))
            }
    }
}
