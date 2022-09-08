package com.daily.view.api.configuration.auth.jwt

import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

fun extract(serverWebExchange: ServerWebExchange): Mono<String> =
    Mono.justOrEmpty(serverWebExchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
