package com.teamsparta.basicclass.domain.controller.repository.model

import com.teamsparta.basicclass.domain.dto.TodoResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "todos-ryu")
data class Todos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val title: String = "",
    val description: String = "",
    val writer: String = "",
){
    fun toResponse() = TodoResponse(
        id = id ?: throw RuntimeException("ID is null"),
        title = title,
        description = description,
        writer = writer,
    )
}

//val todo = Todos(id, title, description, witer)
//Todos()