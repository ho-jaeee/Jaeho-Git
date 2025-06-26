package com.jaeho.CRUD_project.entity;

import com.jaeho.CRUD_project.dto.PostRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name ="posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;
    private String postPassword;

    //생성자
    public Post(String title, String content, String author, String postPassword)
    {
        this.title = title;
        this.content = content;
        this.author = author;
        this.postPassword = postPassword;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    public static Post from(PostRequestDTO dto) {
        return new Post(
                dto.getTitle(),//제목
                dto.getContent(),//내용
                dto.getAuthor(),//작성자
                dto.getPostPassword()//비밀번호
        );
    }
}






