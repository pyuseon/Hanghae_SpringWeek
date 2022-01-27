package com.sparta.board.controller;


import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.domain.PostRequestDto;
import com.sparta.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@RestController
public class PostController {
    private final PostRepository postRepository; // 생성 조회 삭제
    private final PostService postService; // 업데이트 (변경)

    @PostMapping("/api/posts")
    // @RequestBody 요청이 들어있는 바디에 들어있는 친구를 가져와라
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/posts")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping ("/api/posts/{id}")
    public Post showOnePost(@PathVariable Long id) {
        Post post;
        post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 게시글이 없습니다.")
        );
        return post;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }
}
