package com.daily.view.api.entity.member

import com.daily.view.api.common.BaseTimeEntity
import java.time.OffsetDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "members")
class Members(
    @Column(nullable = false, length = 50, unique = true)
    val email: String,
    @Column(nullable = false, length = 10, unique = true)
    var nickname: String,
    @Column(nullable = false, length = 100)
    var password: String
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var delete: Boolean = false

    var lastLoginAt: OffsetDateTime? = null

    @OneToOne(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    var memberDetail: MemberDetail? = null
}
