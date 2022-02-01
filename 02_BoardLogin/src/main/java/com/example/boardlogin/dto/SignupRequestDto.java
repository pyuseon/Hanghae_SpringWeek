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
    @Pattern(regexp="(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])",
            message = "유저네임은 영문과 숫자만 허용합니다.")
    @Size(min =3, message = "닉네임은 2자 이상으로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min =4, message = "비밀번호는 4자 이상으로 입력해주세요.")
    private String password;

    private String email;


    @Builder
    public SignupRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
