package com.demo.todoservice.service.impl;

import com.demo.todoservice.constants.TodoConstants;
import com.demo.todoservice.dto.TodoDto;
import com.demo.todoservice.entity.Todo;
import com.demo.todoservice.exception.ResourceNotFoundException;
import com.demo.todoservice.mapper.TodoMapper;
import com.demo.todoservice.repository.TodoRepository;
import com.demo.todoservice.service.ITodoService;
import com.demo.todoservice.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements ITodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto createTodo(UUID creatorId, TodoDto todoDto) {
        log.debug("createTodo() called --->");
        Todo newTodo = TodoMapper.mapDtoToEntity(todoDto, new Todo());
        newTodo.setCreatorId(creatorId);
        Todo savedTodo = todoRepository.save(newTodo);
        return TodoMapper.mapEntityToDto(savedTodo, new TodoDto());
    }

    @Override
    public TodoDto fetchTodo(UUID creatorId, UUID todoId) {
        log.debug("fetchTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        return TodoMapper.mapEntityToDto(todo, new TodoDto());
    }

    @Override
    public Page<TodoDto> fetchTodoPage(UUID creatorId, Integer pageNumber, Integer pageSize, String sortBy) {
        log.debug("fetchTodoPage() called --->");
        PageRequest pageRequest = PageUtil.buildPageRequest(pageNumber, pageSize, sortBy);
        Page<Todo> todoPage = todoRepository.findAllByCreatorId(creatorId, pageRequest);
        return todoPage.map(todo -> TodoMapper.mapEntityToDto(todo, new TodoDto()));
    }

    @Override
    public boolean updateTodo(UUID creatorId, UUID todoId, TodoDto todoDto) {
        log.debug("updateTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        todoRepository.save(TodoMapper.mapDtoToEntity(todoDto, todo));
        return true;
    }

    @Override
    public boolean deleteTodo(UUID creatorId, UUID todoId) {
        log.debug("deleteTodo() called --->");
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException(TodoConstants.RESOURCE_NAME, TodoConstants.TODO_ID, todoId.toString())
        );
        todoRepository.deleteById(todo.getId());
        return true;
    }
}
