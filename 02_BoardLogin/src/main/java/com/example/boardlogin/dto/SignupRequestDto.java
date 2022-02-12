package com.example.boardlogin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
//    https://coding-factory.tistory.com/529
    // 원래는 DTO에서 유효성 검사를 처리했는데 이 결과를 어떻게 프론으로 보내줄지 모르겠습니다.
//    @NotBlank(message = "유저네임을 입력해 주세요")
//    @Pattern(regexp= "^[a-zA-Z0-9]*$",    //"(.*[0-9])(.*[a-zA-Z]).{3,20}",
//            message = "유저네임은 영문과 숫자만 허용합니다. 2글자 이상이어야 합니다. ")
//    @Size(min =3, message = "유저네임은 3자 이상으로 입력해주세요")
    private String username;

//    @NotBlank(message = "비밀번호를 입력해 주세요.")
//    @Size(min =4, message = "비밀번호는 4자 이상으로 입력해주세요.")
    private String password;

    private String passwordCheck;


    @Builder
    public SignupRequestDto(String username,  String password,String passwordCheck) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;

    }
}
