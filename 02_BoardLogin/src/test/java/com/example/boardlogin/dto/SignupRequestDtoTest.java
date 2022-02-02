package com.example.boardlogin.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignupRequestDtoTest {
    private String username;
    private String password;
    private String passwordCheck;

    @BeforeEach
    void setup() {
        username = "test01";
        password = "password01";
        passwordCheck = "password01";
    }

    @Test
    @DisplayName("정상 케이스")
    void createUser_Normal() {


// given
        SignupRequestDto requestDto = new SignupRequestDto(
                username,
                password,
                passwordCheck
        );


// then
        assertEquals(username, requestDto.getUsername());
        assertEquals(password, requestDto.getPassword());
        assertEquals(passwordCheck, requestDto.getPasswordCheck());
    }
}