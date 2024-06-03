package com.teamsparta.basicclass.domain.service

import com.sun.tools.javac.comp.Todo
import com.teamsparta.basicclass.domain.controller.repository.model.TodoRepository
import com.teamsparta.basicclass.domain.controller.repository.model.Todos
import com.teamsparta.basicclass.domain.dto.CreateTodoDto
import com.teamsparta.basicclass.domain.dto.TodoResponse
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    val todoRepository: TodoRepository,
    //생성자 주입방식 -> 생성자를 통해서 주입 받는다.
) : TodoService {
    override fun createTodo(createTodoDto: CreateTodoDto): TodoResponse {
        val todo = Todos(
            title = createTodoDto.title,
            description = createTodoDto.description,
            writer = createTodoDto.writer,
        )

        val result = todoRepository.save(todo)

        return result.toResponse()

        //엘비스 연산자를 이용해서 null 체크를 할 수 있다.
    }

    override fun getTodo() {
        TODO("Not yet implemented")
    }

    override fun updateTodo() {
        TODO("Not yet implemented")
    }

    override fun deleteTodo() {
        TODO("Not yet implemented")
    }
}

//notification service -> api 형태로 개발됨
//notification service module -> 코드 내부 api를 호출하지만 추상화된 정보를 전하는 함수