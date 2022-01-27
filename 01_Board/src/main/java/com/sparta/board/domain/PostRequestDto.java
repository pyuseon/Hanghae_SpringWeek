package com.sparta.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String username;
    private final String title;
    private final String contents;
    private LocalDateTime createdAt;


}
