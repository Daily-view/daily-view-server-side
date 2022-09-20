package com.daily.view.api

import com.daily.view.api.dto.member.MemberDto
import com.daily.view.api.entity.member.Members
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

fun MultipartFile.getUUIDFileName(): String {
    val fileName = this.name
    val ext = fileName.substring(fileName.lastIndexOf(".") + 1)
    return "${UUID.randomUUID()}.$ext"
}

fun String.convertPublicURL(): String =
    this.replace("https://storage.googleapis.com/download/storage/v1", "https://firebasestorage.googleapis.com/v0")

fun Members.toDto() =
    MemberDto(this.id!!, this.nickname, this.email, this.lastLoginAt, this.createdAt, this.updatedAt)
