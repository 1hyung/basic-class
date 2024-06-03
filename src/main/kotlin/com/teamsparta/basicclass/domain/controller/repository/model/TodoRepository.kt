package com.teamsparta.basicclass.domain.controller.repository.model

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todos, Long> {

}

//왜 구현체가 없지?
//-> spring jpa를 사용하면 자동으로 구현체를 만들어줌