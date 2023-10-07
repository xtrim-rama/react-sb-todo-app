package com.demo.todoservice.service;

import com.demo.todoservice.dto.TodoDto;

import java.util.UUID;

public interface ITodoService {
    TodoDto createTodo(UUID creatorId, TodoDto todoDto);

    TodoDto fetchTodo(UUID creatorId, UUID todoId);

    boolean updateTodo(UUID creatorId, UUID todoId, TodoDto todoDto);

    boolean deleteTodo(UUID creatorId, UUID todoId);
}
