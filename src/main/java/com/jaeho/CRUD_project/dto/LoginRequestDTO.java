package com.jaeho.CRUD_project.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class LoginRequestDTO {
    private String username;
    private String password;
}
