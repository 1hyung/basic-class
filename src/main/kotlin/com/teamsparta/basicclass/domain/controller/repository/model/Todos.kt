package com.teamsparta.basicclass.domain.controller.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "todos-ryu")
data class Todos(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val writer: String = "",
)

//val todo = Todos(id, title, description, witer)
//Todos()