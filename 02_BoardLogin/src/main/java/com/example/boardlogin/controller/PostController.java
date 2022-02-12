package com.example.boardlogin.controller;

import com.example.boardlogin.dto.PostRequestDto;
import com.example.boardlogin.model.Post;
import com.example.boardlogin.repository.PostRepository;
import com.example.boardlogin.security.UserDetailsImpl;
import com.example.boardlogin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;


    @PostMapping("/posting")
    // @RequestBody 요청이 들어있는 바디에 들어있는 친구를 가져와라
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인 되어있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUser().getUsername();
        Post post = new Post(requestDto, userId, username);
        return  postRepository.save(post);
    }

    // 전체 포스트 가져오기
    @GetMapping("/posts")
    public List<Post> readPost(){
        return
                postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시물 하나만 조회
    @GetMapping ("/posts/detail/{id}")
    public Post showOnePost(@PathVariable Long id) {
        Post post;
        post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 게시글이 없습니다.")
        );
        return post;
    }

    // 게시물 수정하기
    @PutMapping("/posting/detail/{id}")
    public Long updatePost(@PathVariable Long id,
                           @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 게시물 삭제하기
    @DeleteMapping("/posting/detail/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }

    //로그인 사용자 정보 받아오기 (더 좋은 방법이 없을까요?)
    @GetMapping("/post/current")
    public String getUserinfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUser().getUsername();
    }
}
