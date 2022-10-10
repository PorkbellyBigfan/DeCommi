package org.zerock.decommi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TodoDTO;
import org.zerock.decommi.entity.member.Todo;
import org.zerock.decommi.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/todo/")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
  private final TodoService todoService;

  @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TodoDTO>> getTodoList(@RequestBody PageRequestDTO dto) {
    // 필요한값 page, size, type, keyword, writer
    PageRequestDTO.builder().page(dto.getPage()).size(dto.getSize())
        .type(dto.getType()).keyword(dto.getKeyword()).build();
    PageResultDTO<TodoDTO, Todo> result = todoService.getTodoList(dto);
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> registerTodo(@RequestBody TodoDTO dto) {
    return new ResponseEntity<>(todoService.registerTodo(dto), HttpStatus.OK);
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> editTodo(@RequestBody TodoDTO dto) {
    return new ResponseEntity<>(todoService.editTodo(dto), HttpStatus.OK);
  }

  @RequestMapping(value = "/checkuncheck", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> checkUncheckTodo(@RequestBody TodoDTO dto) {
    return new ResponseEntity<>(todoService.checkUncheckTodo(dto), HttpStatus.OK);
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> deleteTodo(@RequestBody TodoDTO dto) {
    return new ResponseEntity<>(todoService.deleteTodo(dto), HttpStatus.OK);
  }
}
