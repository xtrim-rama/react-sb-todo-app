package com.demo.todoservice.dto.common;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private List<Map<String, String>> validationErrors;
    private LocalDateTime errorTime;
}
