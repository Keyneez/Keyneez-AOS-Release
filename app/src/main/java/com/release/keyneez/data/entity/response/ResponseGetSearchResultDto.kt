package com.release.keyneez.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetSearchResultDto(
    @SerialName("content_key") val contentKey: Int,
    @SerialName("content_title") val contentTitle: String,
    @SerialName("start_at") val start: String?,
    @SerialName("end_at") val end: String?,
    @SerialName("content_img") val contentImg: String?,
    @SerialName("liked") val liked: Boolean
)
