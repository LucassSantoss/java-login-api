package com.lucas.login_api.controllers;

import com.lucas.login_api.domain.user.User;
import com.lucas.login_api.dto.UserResponseDTO;
import com.lucas.login_api.infra.security.CustomUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(@AuthenticationPrincipal User user) {
        UserResponseDTO response = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(response);
    }
}
