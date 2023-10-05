package com.demo.todoservice.dto;

import com.demo.todoservice.entity.TodoStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {

    private UUID id;

    @NotEmpty(message = "Title can not be null or empty")
    private String title;

    private String description;

    private TodoStatus todoStatus;

    private UUID creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
