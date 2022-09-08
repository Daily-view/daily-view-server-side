package com.daily.view.api.configuration.auth.bearer

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

class BearerTokenReactiveAuthenticationManager : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        return Mono.just(authentication)
    }
}
