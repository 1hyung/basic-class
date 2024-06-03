package com.teamsparta.basicclass.domain.dto

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String,
    val writer: String,
)
