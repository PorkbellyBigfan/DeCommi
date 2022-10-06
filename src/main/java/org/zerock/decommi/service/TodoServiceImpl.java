package org.zerock.decommi.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TodoDTO;
import org.zerock.decommi.entity.member.QTodo;
import org.zerock.decommi.entity.member.Todo;
import org.zerock.decommi.repository.TodoRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
  private final TodoRepository todoRepository;
  @Override
  public String registerTodo(TodoDTO dto) {
    Todo result = dtoToEntity(dto);
    todoRepository.save(result); // 여기서 dino 생성됨
    return result.getTodoId().toString();
  }

  @Override
  @Transactional
  public Boolean editTodo(TodoDTO dto) {
    Todo originalTodo = todoRepository.findByTodoId(dto.getTodoId());
    TodoDTO getByTodoId = entityToDTO(originalTodo);
    getByTodoId.setTodoTitle(dto.getTodoTitle());
    getByTodoId.setTodoContent(dto.getTodoContent());
    getByTodoId.setTodoStatus(dto.isTodoStatus());
    Todo modifiedTodo = dtoToEntity(getByTodoId);
    todoRepository.save(modifiedTodo);
    return true;
  }

  @Override
  @Transactional
  public Boolean checkUncheckTodo(TodoDTO dto) {
    boolean changedStatus;
    Todo originalTodo = todoRepository.findByTodoId(dto.getTodoId());
    log.info(originalTodo.isTodoStatus());
    if(originalTodo.isTodoStatus()==true){
      changedStatus = false;
    }else{
      changedStatus = true;
    }
    TodoDTO getByTodoId = entityToDTO(originalTodo);
    getByTodoId.setTodoStatus(changedStatus);
    Todo changedTodo = dtoToEntity(getByTodoId);
    todoRepository.save(changedTodo);
    return true;
  }

  @Override
  @Transactional
  public Boolean deleteTodo(TodoDTO dto) {
    Optional<Todo> check = todoRepository.getTodoByTodoIdAndWriter(dto.getTodoId(), dto.getWriter());
    if(check.isPresent()){
      todoRepository.deleteById(dto.getTodoId());
      return true;
    }else{
      return false;
    }
  }

  @Override
  public PageResultDTO<TodoDTO, Todo> getTodoList(PageRequestDTO requestDTO) {
    Pageable pageable = requestDTO.getPageable(Sort.by("todoId").descending());
    BooleanBuilder booleanBuilder = getSearch(requestDTO);
    Page<Todo> result = todoRepository.findAll(booleanBuilder, pageable); 
    Function<Todo, TodoDTO> fn = new Function<Todo, TodoDTO>(){
      @Override
      public TodoDTO apply(Todo t) {
        return entityToDTO(t);
      }
    };
    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO requestDTO){
    String keyword = requestDTO.getKeyword();
    String type = requestDTO.getType();
    String writer = requestDTO.getWriter();
    QTodo qTodo = QTodo.todo;

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = qTodo.todoId.gt(0L).and(qTodo.writer.eq(writer));
    booleanBuilder.and(expression);
    if(type == null || type.trim().length()==0){
      return booleanBuilder;
    }
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if(type.contains("k")){  //keyword 의 k
      conditionBuilder
        .or(qTodo.todoTitle.contains(keyword))
        .or(qTodo.todoContent.contains(keyword));
    }else if(type.contains("t")){ //true 의 t
      conditionBuilder
        .and(qTodo.todoStatus.isTrue());
    }else if(type.contains("f")){ //false 의 f
      conditionBuilder
        .and(qTodo.todoStatus.isFalse());
    }
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }
}
