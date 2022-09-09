//package com.daily.view.api.fetcher
//
//import com.daily.view.api.dto.CreateMemberInput
//import com.daily.view.api.service.member.MemberService
//import org.springframework.graphql.data.method.annotation.Argument
//import org.springframework.graphql.data.method.annotation.QueryMapping
//import org.springframework.stereotype.Controller
//import reactor.core.publisher.Mono
//
//@Controller
//class MemberQueryFetcher(
//    private val memberService: MemberService
//) {
//
//    @QueryMapping(value = "createMember")
//    fun create(@Argument input: CreateMemberInput): Mono<Boolean> = memberService.create(input)
//}
