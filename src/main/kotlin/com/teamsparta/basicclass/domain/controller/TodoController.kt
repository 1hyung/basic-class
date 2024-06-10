package com.teamsparta.basicclass.domain.controller

import com.teamsparta.basicclass.domain.controller.request.CreateTodoRequest
import com.teamsparta.basicclass.domain.controller.request.UpdateTodoRequest
import com.teamsparta.basicclass.domain.dto.CreateTodoDto
import com.teamsparta.basicclass.domain.dto.TodoResponse
import com.teamsparta.basicclass.domain.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController//HTTP로 통신을 할건데 주소에 맞게 잘 들어왔는데 확인하기 위해 mapping하는 약할
@RequestMapping("/api/v1/todos")//항상 이 주소로 시작되는 녀석들만 mapping
class TodoController(
    val todoService: TodoService, // 생성자 주입 DI TodoServiceImpl
) {
    //http://localhost:8080/api/v1/todos
    //http://주소:포트/requestMapping/PostMapping

    @PostMapping
    fun createTodo(
        @RequestBody request: CreateTodoRequest,
    ): ResponseEntity<TodoResponse> {
        //service에 있는 createTodo함수를 호출
        //request를 service를 넘겨준다.
        //비즈니스 로직을 실행시키기 위해

        val result = todoService.createTodo(
            CreateTodoDto(
                title = request.title,
                description = request.description,
                writer = request.writer,
            )
        )
        return ResponseEntity.ok(result)
    } //RestAPI 하나 완성

    // http://localhost:8080/api/v1/todos/123 ? value=123 ? 앞이 PathVariable ? 뒤 queryParm
    @GetMapping("/{todoId}") //1
    fun getTodo(
        @PathVariable todoId: Long, //PathVariable 경로상에서 //2 1,2번 통일
    ): ResponseEntity<TodoResponse> {
        val result = todoService.getTodo(todoId)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }// 입력하는 제대로 응답하는 것

    @GetMapping
    fun getTodos(): ResponseEntity<List<TodoResponse>> {
        // val result = todoService.getTodos()
        val result = todoService.getTodos()
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
    /*

        [ //리스트
            {//객체
                "id": 0,
                "title": "string",
                "description": "string",
                "writer": "string"
            }
        ]
    */

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody request: UpdateTodoRequest,
    ): ResponseEntity<TodoResponse> { //<리턴타입> 항상 명심해야함
        val result = todoService.updateTodo(
            id = todoId,
            title = request.title,
            description = request.description,
        )
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*    fun 함수명 (매개변수): 반환타입 {
            return 반환값
        }
        반한값의 자료형과 반환타입이 일치해야 함.*/
}