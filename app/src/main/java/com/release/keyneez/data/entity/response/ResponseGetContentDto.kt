package com.release.keyneez.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResponseGetContentDto(
    @SerialName("content_pk") val contentPk: Int,
    @SerialName("title") val title: String,
    @SerialName("category") val category: String,
    @SerialName("img") val img: String?,
    @SerialName("start_at") val start: String?,
    @SerialName("end_at") val end: String?,
    val Likes: List<Liked>
) {
    @Serializable
    data class Liked(
        @SerialName("liked_pk") val likedPk: Int,
        @SerialName("user") val user: Int,
        @SerialName("content") val content: Int
    )
}
