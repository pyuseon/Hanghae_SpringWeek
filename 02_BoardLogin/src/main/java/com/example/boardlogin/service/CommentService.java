package com.example.boardlogin.service;

import com.example.boardlogin.dto.CommentRequestDto;
import com.example.boardlogin.model.Comment;
import com.example.boardlogin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment;
    }


    @Transactional // 업데이트에 꼭 반영 해줘야 된다 !
    public Long update(Long id, CommentRequestDto requestDto) {// public 반환값 update(재료)
        // NullPointerException :  내가 가르키는 것이 없다.
        // IllegalArgumentException : 내가 찾는것이 잘못되었다.
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return id;
    }
}
