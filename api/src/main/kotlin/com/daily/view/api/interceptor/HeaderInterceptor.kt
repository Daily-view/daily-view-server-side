package com.daily.view.api.interceptor

import org.springframework.graphql.server.WebGraphQlInterceptor
import org.springframework.graphql.server.WebGraphQlRequest
import org.springframework.graphql.server.WebGraphQlResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class HeaderInterceptor : WebGraphQlInterceptor {
    override fun intercept(request: WebGraphQlRequest, chain: WebGraphQlInterceptor.Chain): Mono<WebGraphQlResponse> {
        return chain.next(request).doOnNext { response ->
            var value: String? = response.executionInput.graphQLContext.get("token")
            if (value != null) {
                var cookie = ResponseCookie.from("token", value)
                    .maxAge(Duration.ofDays(1L))
                    .build()
                response.responseHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString())
            }
        }
    }
}
