package com.example.boardlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

    // AllArgsConstructur가 아래 코드를 대신 생성해 준다.
//    public KakaoUserInfoDto(Long id, String nickname, String email) {
//        this.id = id;
//        this.nickname = nickname;
//        this.email = email;
//    }

}
