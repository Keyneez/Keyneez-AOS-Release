package com.release.keyneez.data.entity.response

import kotlinx.serialization.SerialName

data class ResponseGetLikeDto(
    @SerialName("content_pk") val content: Int,
    @SerialName("title") val title: String,
    @SerialName("category") val category: String,
    @SerialName("img") val img: String?,
    @SerialName("start_at") val start: String?,
    @SerialName("end_at") val end: String?
)
