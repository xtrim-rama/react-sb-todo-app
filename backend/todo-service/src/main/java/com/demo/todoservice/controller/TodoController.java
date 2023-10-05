package com.demo.todoservice.controller;

import com.demo.todoservice.constants.CommonConstants;
import com.demo.todoservice.constants.TodoConstants;
import com.demo.todoservice.dto.TodoDto;
import com.demo.todoservice.dto.common.ResponseDto;
import com.demo.todoservice.service.ITodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class TodoController {

    private final ITodoService iTodoService;

    @PostMapping(TodoConstants.TODO_PATH)
    public ResponseEntity<ResponseDto> createTodo(@Validated @RequestBody TodoDto todoDto) {
        log.debug("createTodo() called --->");
        iTodoService.createTodo(todoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode(CommonConstants.STATUS_201)
                        .statusMsg(CommonConstants.MSG_201_CREATED)
                        .build());
    }

    @GetMapping(TodoConstants.TODO_PATH_ID)
    public ResponseEntity<TodoDto> fetchTodo(@PathVariable("todoId") UUID todoId) {
        log.debug("fetchTodo() called --->");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iTodoService.fetchTodo(todoId));
    }

    @PutMapping(TodoConstants.TODO_PATH_ID)
    public ResponseEntity<ResponseDto> updateTodo(@PathVariable("todoId") UUID todoId,
                                                  @Validated @RequestBody TodoDto todoDto) {
        log.debug("updateTodo() called --->");
        boolean isUpdated = iTodoService.updateTodo(todoId, todoDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(CommonConstants.STATUS_200)
                            .statusMsg(CommonConstants.MSG_200_UPDATED)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode(CommonConstants.STATUS_417)
                            .statusMsg(CommonConstants.MSG_417_UPDATE_FAILED)
                            .build());
        }
    }

    @DeleteMapping(TodoConstants.TODO_PATH_ID)
    public ResponseEntity<ResponseDto> deleteTodo(@PathVariable("todoId") UUID todoId) {
        log.debug("deleteTodo() called --->");
        boolean isDeleted = iTodoService.deleteTodo(todoId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(CommonConstants.STATUS_200)
                            .statusMsg(CommonConstants.MSG_200_DELETED)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode(CommonConstants.STATUS_417)
                            .statusMsg(CommonConstants.MSG_417_DELETE_FAILED)
                            .build());
        }
    }
}
