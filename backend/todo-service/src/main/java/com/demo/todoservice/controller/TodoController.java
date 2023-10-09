package com.demo.todoservice.controller;

import com.demo.todoservice.constants.CommonConstants;
import com.demo.todoservice.constants.TodoConstants;
import com.demo.todoservice.dto.TodoDto;
import com.demo.todoservice.dto.common.ResponseDto;
import com.demo.todoservice.security.jwt.JWTUtils;
import com.demo.todoservice.service.ITodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "${todo-service.client.url}")
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class TodoController {

    private final ITodoService iTodoService;
    private final JWTUtils jwtUtils;

    @PostMapping(TodoConstants.TODO_PATH)
    public ResponseEntity<ResponseDto> createTodo(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth,
                                                  @Validated @RequestBody TodoDto todoDto) {
        log.debug("createTodo() called --->");
        UUID creatorId = jwtUtils.getUserIdFromJwtToken(headerAuth);
        TodoDto savedTodo = iTodoService.createTodo(creatorId, todoDto);
        return ResponseEntity
                .created(URI.create(TodoConstants.TODO_PATH + "/" + savedTodo.getId().toString()))
                .body(ResponseDto.builder()
                        .statusCode(CommonConstants.STATUS_201)
                        .statusMsg(CommonConstants.MSG_201_CREATED)
                        .build());
    }

    @GetMapping(TodoConstants.TODO_PATH_ID)
    public ResponseEntity<TodoDto> fetchTodo(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth,
                                             @PathVariable("todoId") UUID todoId) {
        log.debug("fetchTodo() called --->");
        UUID creatorId = jwtUtils.getUserIdFromJwtToken(headerAuth);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iTodoService.fetchTodo(creatorId, todoId));
    }

    @GetMapping(TodoConstants.TODO_PATH)
    public ResponseEntity<Page<TodoDto>> fetchTodoPage(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth,
                                                       @RequestParam(required = false) Integer pageNumber,
                                                       @RequestParam(required = false) Integer pageSize,
                                                       @RequestParam(required = false) String sortBy) {
        log.debug("fetchTodoPage() called --->");
        UUID creatorId = jwtUtils.getUserIdFromJwtToken(headerAuth);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iTodoService.fetchTodoPage(creatorId, pageNumber, pageSize, sortBy));
    }

    @PutMapping(TodoConstants.TODO_PATH_ID)
    public ResponseEntity<ResponseDto> updateTodo(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth,
                                                  @PathVariable("todoId") UUID todoId,
                                                  @Validated @RequestBody TodoDto todoDto) {
        log.debug("updateTodo() called --->");
        UUID creatorId = jwtUtils.getUserIdFromJwtToken(headerAuth);
        boolean isUpdated = iTodoService.updateTodo(creatorId, todoId, todoDto);
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
    public ResponseEntity<ResponseDto> deleteTodo(@RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth,
                                                  @PathVariable("todoId") UUID todoId) {
        log.debug("deleteTodo() called --->");
        UUID creatorId = jwtUtils.getUserIdFromJwtToken(headerAuth);
        boolean isDeleted = iTodoService.deleteTodo(creatorId, todoId);
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
