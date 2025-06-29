package com.jaeho.CRUD_project.dto;

import com.jaeho.CRUD_project.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseDTO from (Post post) {
        return new PostResponseDTO(
          post.getId(),
          post.getTitle(),
          post.getContent(),
          post.getAuthor(),
          post.getCreatedAt(),
          post.getUpdatedAt()
        );
    }

}
