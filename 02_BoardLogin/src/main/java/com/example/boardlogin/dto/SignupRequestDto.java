package com.example.boardlogin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "유저네임을 입력해 주세요")
    @Pattern(regexp= "^[a-zA-Z0-9]*$",    //"(.*[0-9])(.*[a-zA-Z]).{3,20}",
            message = "유저네임은 영문과 숫자만 허용합니다. 2글자 이상이어야 합니다. ")
    @Size(min =3, message = "유저네임은 3자 이상으로 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min =4, message = "비밀번호는 4자 이상으로 입력해주세요.")
    private String password;

    private String passwordCheck;


    @Builder
    public SignupRequestDto(String username, String passwordCheck, String password) {
        this.username = username;
        this.passwordCheck = passwordCheck;
        this.password = password;
    }
}
