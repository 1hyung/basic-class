package com.teamsparta.basicclass.domain.service

import com.teamsparta.basicclass.domain.controller.repository.model.TodoRepository
import com.teamsparta.basicclass.domain.controller.repository.model.Todos
import com.teamsparta.basicclass.domain.dto.CreateTodoDto
import com.teamsparta.basicclass.domain.dto.TodoResponse
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

//Spring Cpmponent Scan
//Component 발견
//TodeServiceImpl은 청사진 (인터페이스) TodoService로 구현되어 있구나
//나중에 todoService로 생성자, 필드, setter 주입을 하는 경우 todoServiceImpl로 넝허줘야지
@Service
class TodoServiceImpl : TodoService {
    val todoRepository: TodoRepository

    //생성자 주입방식 -> 생성자를 통해서 주입 받는다.
    //setter 주입방식 -> setter를 통해서 주입받는다.
    //필드 주입방식 -> 필드를 통해서 주입받는다.
    constructor(todoRepository: TodoRepository) {
        this.todoRepository = todoRepository
    }

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

    override fun getTodo(id: Long): TodoResponse {
        val todo = todoRepository.findById(id).getOrNull() ?: throw RuntimeException("해당 아이디의 todo가 존재하지 않습니다.")
        return todo.toResponse()
    }

    override fun getTodos(): List<TodoResponse> {
        val todos = todoRepository.findAll()
        //TODO: 페이징 처리가 필요함 (굉장히 위험한 코드임)
        //모든 데이터를 다 가져옴 -> 1억건이 있다면 1억건 모두 가져옴
        //어떤 문제가 발생할까?

        //데이터가 많을 수록 성능이 떨어짐 -> 페이징 처리를 해야함
        //데이터가 많을 수록 성능이 떨어짐 -> 네트워크 통신과정에서 오류가 발생하여 재시도를 하는 경우가 발생
        //로컬pc에서 작업 중인데 -> local pc(server pc) -> 메모리 이슈 발생
        //1억건의 데이터가 필요가 없음 -> 페이징 처리가 필요함

        //데이터가 많아질 수록 데이터 유실이 발생할 수 있음

        //todos -> [todo1, todo2, todo3, todo4]
        //map -> [todo1.toResponse(), todo2.toResponse(), todo3.toResponse(), todo4.toResponse()]
        val result = todos.map { it.toResponse() }
        return result
    }

    @Transactional
    override fun updateTodo(
        id: Long, //어떤 todo를 수정할지 알아야함
        title: String?, // 수정할 title
        description: String?, // 수정할 description -> null 일 수도 있음. null 일 경우는 현상 유지
    ): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id) ?: throw RuntimeException("해당 아이디의 $id 가  존재하지 않습니다.") //ID가 띄워야 함.

        /*        todo.title = title ?: todo.title
                todo.description = description ?: todo.description*/
        todo.updateTitleOrDescription(title, description)

        //코드 따라치다가 놓침 나중에 수업 다시 돌려보기
        return todo.toResponse()
    }

    override fun deleteTodo() {
        TODO("Not yet implemented")
    }
}

//notification service -> api 형태로 개발됨
//notification service module -> 코드 내부 api를 호출하지만 추상화된 정보를 전하는 함수