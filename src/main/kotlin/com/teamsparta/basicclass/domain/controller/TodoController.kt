package com.teamsparta.basicclass.domain.controller


import com.teamsparta.basicclass.domain.controller.request.TodoRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController//HTTP로 통신을 할건데 주소에 맞게 잘 들어왔는데 확인하기 위해 mapping하는 약할
@RequestMapping("/api/v1/todos")//항상 이 주소로 시작되는 녀석들만 mapping
class TodoController {
    //http://localhost:8080/api/v1/todos
    //http://주소:포트/requestMapping/PostMapping
    @PostMapping
    fun createTodo(
        @RequestBody request: TodoRequest
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok(true)
    } //RestAPI 하나 완성

    // http://localhost:8080/api/v1/todos/123 ? value=123 ? 앞이 PathVariable ? 뒤 queryParm
    @GetMapping("/{id}") //1
    fun getTodo(
        @PathVariable id: Long, //PathVariable 경로상에서 //2 1,2번 통일
    ): ResponseEntity<Long> {
        return ResponseEntity.ok(id)
    }// 입력하는 제대로 응답하는 것
}