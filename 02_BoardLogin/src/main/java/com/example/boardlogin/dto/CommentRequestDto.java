package com.example.boardlogin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
//    private final String username;
//    private final String title;
    private final String contents;
    private final Long postId;
    private final Long id;


/*
    private final long id;

    private LocalDateTime modifiedAt;
*/


}