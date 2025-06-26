package com.jaeho.CRUD_project.controller;

import com.jaeho.CRUD_project.dto.PostRequestDTO;
import com.jaeho.CRUD_project.dto.PostResponseDTO;
import com.jaeho.CRUD_project.entity.Post;
import com.jaeho.CRUD_project.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        List<PostResponseDTO> postList = postService.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("postList", postList);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO dto) {
        Post saved = postService.savePost(dto);
        return ResponseEntity.ok(saved);
    }


}