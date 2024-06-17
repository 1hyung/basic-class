package com.teamsparta.basicclass.domain.service

import com.teamsparta.basicclass.domain.dto.CreateTodoDto
import com.teamsparta.basicclass.domain.dto.TodoResponse

interface TodoService {
    fun createTodo(
        createTodoDto: CreateTodoDto
        //매개변수 CreateTodoDto 데이터 베이스에 새로운 todo를 추가하기
    ): TodoResponse

    //리턴 타입으로 TodoResponse 데이터 베이스에 새로운 todo를 추가한 후
    fun getTodo(id: Long): TodoResponse
    fun getTodos(): List<TodoResponse>
    fun updateTodo(
        id: Long,
        title: String?,
        description: String?
    ): TodoResponse // 뭘 할지 청사진을 적는 곳, 이 함수를 사용하려면 어떤 것을 써야지 고민하는 단계

    fun deleteTodo(
        //delete는 title과 description을 매개변수로 받을 필요가 없다. id 만으로 충분!
        id: Long,
    ): TodoResponse
}