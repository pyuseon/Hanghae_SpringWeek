package com.example.boardlogin.model;

import com.example.boardlogin.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;



    public Comment(CommentRequestDto requestDto, Long userId, String username) {
//        this.username = requestDto.getUsername();
//        this.title = requestDto.getTitle();
        this.postId = requestDto.getPostId();
        this.id = requestDto.getId();
        this.contents = requestDto.getContents();
        this.userId = userId;
        this.username = username;
    }

    public Comment(Long id, Long postId, String contents) {
//        this.username = username;
//        this.title = username;
        this.postId = postId;
        this.id = id;
        this.contents = contents;
    }

    public void update(CommentRequestDto requestDto) {
//        this.username = requestDto.getUsername();
//        this.title = requestDto.getTitle();
        this.postId = requestDto.getPostId();
        this.id = requestDto.getId();
        this.contents = requestDto.getContents();
    }
}
