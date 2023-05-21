package com.release.keyneez.domain.model

data class Activity(
    val id: Int,
    val background: Int,
    val category: String,
    val title: String,
    val date: String,
    val liked: Boolean
)
