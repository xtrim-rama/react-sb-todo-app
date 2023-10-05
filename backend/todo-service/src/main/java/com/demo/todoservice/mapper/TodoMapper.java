package com.demo.todoservice.mapper;

import com.demo.todoservice.dto.TodoDto;
import com.demo.todoservice.entity.Todo;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TodoMapper {

    private TodoMapper() {
    }

    public static TodoDto mapEntityToDto(@NotNull Todo todo, @NotNull TodoDto todoDto) {
        log.debug("mapEntityToDto() called --->");
        todoDto.setId(todo.getId());
        todoDto.setTodoStatus(todo.getTodoStatus());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setCreatedAt(todo.getCreatedAt());
        todoDto.setUpdatedAt(todo.getUpdatedAt());
        return todoDto;
    }

    public static Todo mapDtoToEntity(@NotNull TodoDto todoDto, @NotNull Todo todo) {
        log.debug("mapDtoToEntity() called --->");
        todo.setTodoStatus(todoDto.getTodoStatus());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        return todo;
    }
}
