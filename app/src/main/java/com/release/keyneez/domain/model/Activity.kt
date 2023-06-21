package com.release.keyneez.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val id: Int,
    val background: Int,
    val category: String,
    val title: String,
    val date: String,
    val liked: Boolean
)
