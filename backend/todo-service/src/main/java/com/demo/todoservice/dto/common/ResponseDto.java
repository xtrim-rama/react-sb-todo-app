package com.demo.todoservice.dto.common;

import lombok.Builder;

@Builder
public class ResponseDto {

    private String statusCode;
    private String statusMsg;
}
