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

//    @Autowired
//    public PostController(PostService postService) {
//
//        this.postService = postService;
//    }

    @PostMapping("/posting")
    // @RequestBody 요청이 들어있는 바디에 들어있는 친구를 가져와라
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인 되어있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();
        Post post = new Post(requestDto, userId);
        return  postRepository.save(post);
    }


    @GetMapping("/posts")
    public List<Post> readPost(){
        return
                postRepository.findAllByOrderByModifiedAtDesc();
    }


    @GetMapping ("/posts/detail/{id}")
    public Post showOnePost(@PathVariable Long id) {
        Post post;
        post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 게시글이 없습니다.")
        );
        return post;
    }

    @PutMapping("/posts/detail/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/posts/detail/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }


}
