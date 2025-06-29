package com.jaeho.CRUD_project.controller;

import com.jaeho.CRUD_project.dto.LoginRequestDTO;
import com.jaeho.CRUD_project.dto.SignupRequestDTO;
import com.jaeho.CRUD_project.jwt.JwtUtil;
import com.jaeho.CRUD_project.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO requestDto) {
        try {
            userService.signup(requestDto);
            return ResponseEntity.ok(Map.of("msg", "회원가입 성공", "statusCode", 200));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage(), "statusCode", 400));
        }
    }

    // 로그인
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDto, HttpServletResponse response) {
        try {
            String token = userService.login(requestDto);
            // JWT 토큰을 헤더에 담아서 클라이언트에게 전달
            response.setHeader("Authorization", "Bearer " + token);
            // 성공 응답 반환
            Map<String, Object> success = new HashMap<>();
            success.put("message", "로그인 성공");
            success.put("statusCode", 200);
            return ResponseEntity.ok(success);
        } catch (IllegalArgumentException e) {
            // 실패 응답 반환
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("statusCode", 400);
            return ResponseEntity.badRequest().body(error);
        }

    }


}
