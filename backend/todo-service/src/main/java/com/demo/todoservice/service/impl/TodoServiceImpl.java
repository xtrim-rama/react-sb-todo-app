package com.demo.todoservice.service.impl;

import com.demo.todoservice.constants.TodoConstants;
import com.demo.todoservice.dto.TodoDto;
import com.demo.todoservice.entity.Todo;
import com.demo.todoservice.exception.ResourceNotFoundException;
import com.demo.todoservice.mapper.TodoMapper;
import com.demo.todoservice.repository.TodoRepository;
import com.demo.todoservice.service.ITodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements ITodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        log.debug("createTodo() called --->");
        return TodoMapper.mapEntityToDto(todoRepository.save(TodoMapper.mapDtoToEntity(todoDto, new Todo())), new TodoDto());
    }

    @Override
    public TodoDto fetchTodo(UUID todoId) {
        log.debug("fetchTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        return TodoMapper.mapEntityToDto(todo, new TodoDto());
    }

    @Override
    public boolean updateTodo(UUID todoId, TodoDto todoDto) {
        log.debug("updateTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        todoRepository.save(TodoMapper.mapDtoToEntity(todoDto, todo));
        return true;
    }

    @Override
    public boolean deleteTodo(UUID todoId) {
        log.debug("deleteTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        todoRepository.deleteById(todo.getId());
        return true;
    }
}
