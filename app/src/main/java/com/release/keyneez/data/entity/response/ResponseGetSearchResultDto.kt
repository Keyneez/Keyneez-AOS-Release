package com.release.keyneez.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetSearchResultDto(
    @SerialName("content_pk") val content: Int,
    @SerialName("title") val title: String,
    @SerialName("category") val category: String,
    @SerialName("tag") val tag: String,
    @SerialName("link") val link: String,
    @SerialName("img") val img: String?,
    @SerialName("place") val place: String,
    @SerialName("introduction") val introduction: String,
    @SerialName("inquiry") val inquiry: List<String>?,
    @SerialName("price") val price: List<String>?,
    @SerialName("benefit") val benefit: List<String>?,
    @SerialName("start_at") val start: String?,
    @SerialName("end_at") val end: String?,
    @SerialName("created_at") val created: String?,
    @SerialName("updated_at") val updated: String?,
    var Likes: List<Liked>
) {
    @Serializable
    data class Liked(
        @SerialName("liked_pk") val liked: Int,
        @SerialName("user") val user: Int,
        @SerialName("content") val content: Int
    )
}
