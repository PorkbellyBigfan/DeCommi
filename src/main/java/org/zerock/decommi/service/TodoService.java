package org.zerock.decommi.service;

import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TodoDTO;
import org.zerock.decommi.entity.member.Todo;

public interface TodoService {
  // TODO 등록
  String registerTodo(TodoDTO dto);

  // TODO 삭제
  Boolean deleteTodo(TodoDTO dto);

  // TODO 자체를 변경(제목, 내용)
  Boolean editTodo(TodoDTO dto);

  // TODO 상태변경
  Boolean checkUncheckTodo(TodoDTO dto);

  PageResultDTO<TodoDTO, Todo> getTodoList(PageRequestDTO requestDTO);

  default Todo dtoToEntity(TodoDTO dto) {
    Todo todo = Todo.builder()
        .todoId(dto.getTodoId())
        .writer(dto.getWriter())
        .todoTitle(dto.getTodoTitle())
        .todoContent(dto.getTodoContent())
        .todoStatus(dto.isTodoStatus())
        .build();
    return todo;
  }

  default TodoDTO entityToDTO(Todo entity) {
    TodoDTO dto = TodoDTO.builder()
        .todoId(entity.getTodoId())
        .writer(entity.getWriter())
        .todoTitle(entity.getTodoTitle())
        .todoContent(entity.getTodoContent())
        .todoStatus(entity.isTodoStatus())
        .build();
    return dto;
  }
}
