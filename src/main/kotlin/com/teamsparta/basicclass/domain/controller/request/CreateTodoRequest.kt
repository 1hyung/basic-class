package com.teamsparta.basicclass.domain.controller.request

data class CreateTodoRequest(
    val title: String,
    val description: String,
    val writer: String,

    )