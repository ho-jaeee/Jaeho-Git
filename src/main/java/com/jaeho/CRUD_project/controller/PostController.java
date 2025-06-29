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
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 입력
    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO dto) {
        Post saved = postService.savePost(dto);
        return ResponseEntity.ok(saved);
    }

    //게시글 전체 조회
    @GetMapping("/api/posts")
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        List<PostResponseDTO> postList = postService.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("postList", postList);
        return ResponseEntity.ok(response);
    }

    //게시글 상세조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }


    //게시글 수정
    @PutMapping("/api/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDTO dto) {
        try {
            Post updated = postService.updatePost(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, @RequestBody Map<String, String> request) {

        try {
            String password = request.get("postPassword");
            Post deletedPost = postService.deletePost(id, password);

            Map<String, Object> response = new HashMap<>();
            response.put("msg", "게시글 삭제 성공");
            response.put("statusCode", 200);
            response.put("deletedPostId", deletedPost.getId());
            response.put("deletedPostTitle", deletedPost.getTitle());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}