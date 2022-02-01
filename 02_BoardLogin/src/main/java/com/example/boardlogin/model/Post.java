package com.example.boardlogin.model;

import com.example.boardlogin.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    public Post(PostRequestDto requestDto, Long userId, String username) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userId = userId;
        this.username = username;
    }

    public Post(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }


    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
