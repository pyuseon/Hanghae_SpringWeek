package com.sparta.board.service;

import com.sparta.board.domain.Post;
import com.sparta.board.domain.PostRepository;
import com.sparta.board.domain.PostRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


// 보통 서비스는 업데이트에 사용된다.
@Getter
@RequiredArgsConstructor // final 선언된 아이는 내가 꼭 넣어줄게
@Service
public class PostService {
    private final PostRepository postRepository;


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
