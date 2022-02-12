package com.example.boardlogin.service;

import com.example.boardlogin.dto.PostRequestDto;
import com.example.boardlogin.model.Post;
import com.example.boardlogin.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Transactional // 업데이트에 꼭 반영 해줘야 된다 !
    public Long update(Long id, PostRequestDto requestDto) {// public 반환값 update(재료)
        // NullPointerException :  내가 가르키는 것이 없다.
        // IllegalArgumentException : 내가 찾는것이 잘못되었다.
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return id;
    }
}