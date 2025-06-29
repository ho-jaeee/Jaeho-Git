package com.jaeho.CRUD_project.service;

import com.jaeho.CRUD_project.dto.PostRequestDTO;
import com.jaeho.CRUD_project.dto.PostResponseDTO;
import com.jaeho.CRUD_project.entity.Post;
import com.jaeho.CRUD_project.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //게시글 입력
    @Transactional
    public Post savePost(PostRequestDTO dto) {
        Post post = Post.from(dto);
        return postRepository.save(post);
    }
    //게시글 전체 조회
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
    }

    //게시글 상세조회(ID사용)
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        return PostResponseDTO.from(post);
    }

    //게시글 수정
    @Transactional
    public Post updatePost(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 비밀번호 일치 여부 확인
        if (!post.getPostPassword().equals(dto.getPostPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        return post;
    }

    //게시글 삭제
    @Transactional
    public Post deletePost(Long id, String password) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        if (!post.getPostPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        postRepository.delete(post);
        return post;
    }

}
