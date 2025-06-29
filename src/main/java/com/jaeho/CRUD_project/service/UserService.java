package com.jaeho.CRUD_project.service;


import com.jaeho.CRUD_project.dto.LoginRequestDTO;
import com.jaeho.CRUD_project.dto.SignupRequestDTO;
import com.jaeho.CRUD_project.entity.User;
import com.jaeho.CRUD_project.jwt.JwtUtil;
import com.jaeho.CRUD_project.jwt.ValidationUtil;
import com.jaeho.CRUD_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final JwtUtil jwtUtil; // JWT 유틸

    // 회원가입
    public void signup(SignupRequestDTO requestDto) {
        String username = requestDto.getUsername();
        String rawPassword = requestDto.getPassword();

        // 유효성 검사
        ValidationUtil.validateUsername(requestDto.getUsername());
        ValidationUtil.validatePassword(requestDto.getPassword());

        // 아이디 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 이름입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 유저 저장
        User user = new User(username, encodedPassword);
        userRepository.save(user);
    }

    // 로그인
    public String login(LoginRequestDTO requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 존재 여부 확인
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성 후 반환
        return jwtUtil.createToken(user.getUsername());
    }

}
