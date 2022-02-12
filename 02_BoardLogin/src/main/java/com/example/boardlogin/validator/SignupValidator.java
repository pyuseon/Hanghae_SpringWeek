package com.example.boardlogin.validator;

import com.example.boardlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class SignupValidator {

    private static UserRepository userRepository;

    @Autowired
    public SignupValidator (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        SignupValidator.userRepository = userRepository;
    }
    public static void validateSignupInput(String username, String password, String passwordCheck) {
        String usernamePattern = "^[a-zA-Z0-9]*$"; //숫자만

        // 닉네임 글자 수 확인
        if (!Pattern.matches(usernamePattern, username) || username.length() < 3) {
            throw new NullPointerException("닉네임은 세글자 이상이어야 하며 영어, 숫자만 허용합니다.");
        }

        // 비밀번호 글자수 확인
        if (password.length() < 4) {
            throw new NullPointerException("비밀번호는 4글자 이상 입력해주세요.");
        }

        // 비밀번호에 아이디 문자열 포함 여부 확인
        if (password.contains(username)) {
            throw new NullPointerException("비밀번호에 아이디가 포함되어 있습니다.");
        }

        // 패스워드가 일치 하지 않을때
        if (!password.equals(passwordCheck)) {
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }
    }

}
