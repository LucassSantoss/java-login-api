package com.lucas.login_api.dto;

public record RegisterRequestDTO(
        String name, String email, String password
) {
}
