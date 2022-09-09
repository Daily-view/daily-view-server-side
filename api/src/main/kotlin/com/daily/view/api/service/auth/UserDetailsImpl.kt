package com.daily.view.api.service.auth

import com.daily.view.api.entity.member.Members
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val member: Members) : UserDetails {

    var enabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        AuthorityUtils.createAuthorityList("ROLE_USER")

    override fun getPassword(): String = member.password

    override fun getUsername(): String = member.email

    override fun isAccountNonExpired(): Boolean = enabled

    override fun isAccountNonLocked(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = enabled

    override fun isEnabled(): Boolean = enabled
}
