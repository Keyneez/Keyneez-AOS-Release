package com.release.keyneez.data.entity.response.wrapper

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)
