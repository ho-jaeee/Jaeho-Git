package com.jaeho.CRUD_project.dto;

import com.jaeho.CRUD_project.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDTO {


    private String title;
    private String content;
    private String author;
    private String postPassword;

    // 생성자 (선택사항: 필요한 경우에만)
    public PostRequestDTO(String title, String content, String author, String postPassword) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.postPassword = postPassword;
    }
}
