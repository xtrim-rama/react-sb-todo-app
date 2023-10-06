package com.demo.todoservice.service;

import com.demo.todoservice.dto.TodoDto;

import java.util.UUID;

public interface ITodoService {
    TodoDto createTodo(TodoDto todoDto);

    TodoDto fetchTodo(UUID todoId);

    boolean updateTodo(UUID todoId, TodoDto todoDto);

    boolean deleteTodo(UUID todoId);
}
