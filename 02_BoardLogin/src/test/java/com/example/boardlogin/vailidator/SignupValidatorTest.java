package com.example.boardlogin.vailidator;

import com.example.boardlogin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignupValidatorTest {

    private String username;
    private String password;
    private String passwordCheck;

    @BeforeEach
    void setup() {
        username = "test01";
        password = "password01";
        passwordCheck = "password01";
    }

    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Test
    void validateSignupInput() {
    }



    @Nested
    @DisplayName("실패 케이스")
    class FailCase{
        @Nested
        @DisplayName("회원 ID")
        class userName{
            @Test
            @DisplayName("글자수")
            void fail1(){

                username = "00";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("닉네임은 세글자 이상이어야 하며 영어, 숫자만 허용합니다.", exception.getMessage());
            }

            @Test
            @DisplayName("한글 닉네임")
            void fail2(){

                username = "하이";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("닉네임은 세글자 이상이어야 하며 영어, 숫자만 허용합니다.", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("비밀번호")
        class password {
            @Test
            @DisplayName("비밀번호 글자수")
            void fail1() {
                password = "000";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {
                    SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("비밀번호는 4글자 이상 입력해주세요.", exception.getMessage());
            }
            @Test
            @DisplayName("닉네임 문자열 비밀 번호 포함 여부 확인")
            void fail2() {
                username = "user";
                password = "user0123";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {
                    SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("비밀번호에 아이디가 포함되어 있습니다.", exception.getMessage());
            }


        }

        @Nested
        @DisplayName("비밀번호 일치 여부")
        class passwordCheck {
            @Test
            @DisplayName("비밀번호 확인 일치 여부")
            void fail1() {
                password = "0011";
                passwordCheck = "0000";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {
                    SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
            }
            @Test
            @DisplayName("비밀번호 확인 일치 여부")
            void fail2() {
                password = "0000";
                passwordCheck = "0001";

                Exception exception = assertThrows(NullPointerException.class, () ->
                {
                    SignupValidator.validateSignupInput(username, password, passwordCheck);
                });
                assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
            }


        }

    }

}