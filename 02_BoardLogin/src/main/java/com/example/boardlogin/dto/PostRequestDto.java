package com.example.boardlogin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class PostRequestDto {

    private final String title;
    private final String contents;
    private final String username;
    private final Long userId;
//    private final Long id;
//    private LocalDateTime createdAt;
}
