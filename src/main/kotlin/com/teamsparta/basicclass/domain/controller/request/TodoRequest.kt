package com.teamsparta.basicclass.domain.controller.request


data class TodoRequest(
    val title: String,
    val description: String,
    val writer: String,

)