package com.jaeho.CRUD_project.service;

import com.jaeho.CRUD_project.dto.PostRequestDTO;
import com.jaeho.CRUD_project.dto.PostResponseDTO;
import com.jaeho.CRUD_project.entity.Post;
import com.jaeho.CRUD_project.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post savePost(PostRequestDTO dto) {
        Post post = Post.from(dto);
        return postRepository.save(post);
    }
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }

}
