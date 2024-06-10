package com.teamsparta.basicclass.domain.controller.request

data class UpdateTodoRequest(
    val id: String,
    val title: String?,
    val description: String?,
)