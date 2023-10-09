package com.demo.authservice.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class JwtResponse {
    private final String accessToken;
    private final UUID id;
    private final String username;
    private final String email;
    private final List<String> roles;
    private String tokenType = "Bearer";
}
