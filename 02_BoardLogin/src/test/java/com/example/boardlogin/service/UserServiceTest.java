package com.example.boardlogin.service;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.model.User;
import com.example.boardlogin.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    PasswordEncoder passowordEncoder;

    @Test
    @DisplayName("중복 아이디 테스트")
    void register_Test(){
        SignupRequestDto requestDto = new SignupRequestDto(
                "test01",
                "password01",
                "password01"
        );

        User user = new User(requestDto.getUsername(), requestDto.getPassword());

        when(userRepository.findByUsername(requestDto.getUsername())).thenReturn(Optional.of(user));

        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());

        assertSame(found.get().getUsername(), requestDto.getUsername());

    }

    @Test
    @DisplayName("중복 아이디 테스트")
    void register_Test2(){
        SignupRequestDto requestDto = new SignupRequestDto(
                "test02",
                "password01",
                "password01"
        );

        User user = new User(requestDto.getUsername(), requestDto.getPassword());

        when(userRepository.findByUsername(requestDto.getUsername())).thenReturn(Optional.of(user));

        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());

        assertSame(found.get().getUsername(), requestDto.getUsername());

    }
}