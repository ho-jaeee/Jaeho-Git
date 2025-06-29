package com.jaeho.CRUD_project.jwt;

public class ValidationUtil {

    private static final String USERNAME_PATTERN = "^[a-z0-9]{4,10}$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{8,15}$";

    public static void validateUsername(String username) {
        if (!username.matches(USERNAME_PATTERN)) {
            throw new IllegalArgumentException("유효하지 않은 username입니다. 소문자와 숫자 조합의 4~10자여야 합니다.");
        }
    }

    public static void validatePassword(String password) {
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new IllegalArgumentException("유효하지 않은 password입니다. 대소문자 및 숫자 조합의 8~15자여야 합니다.");
        }
    }
}
