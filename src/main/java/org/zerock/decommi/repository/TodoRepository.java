package org.zerock.decommi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.decommi.entity.member.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>, QuerydslPredicateExecutor<Todo>  {

  //todoId 로 todo조회
  Todo findByTodoId(Long todoId);

  @Query("select todo from Todo todo where todoId=:todoId and writer=:writer ")
  Optional<Todo>getTodoByTodoIdAndWriter(Long todoId, String writer);
}
