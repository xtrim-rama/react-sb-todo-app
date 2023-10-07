package com.demo.todoservice.service;

import com.demo.todoservice.dto.TodoDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ITodoService {
    TodoDto createTodo(UUID creatorId, TodoDto todoDto);

    TodoDto fetchTodo(UUID creatorId, UUID todoId);

    Page<TodoDto> fetchTodoPage(UUID creatorId, Integer pageNumber, Integer pageSize, String sortBy);

    boolean updateTodo(UUID creatorId, UUID todoId, TodoDto todoDto);

    boolean deleteTodo(UUID creatorId, UUID todoId);
}
