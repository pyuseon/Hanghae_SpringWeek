package com.example.boardlogin.service;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.model.User;
import com.example.boardlogin.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new NullPointerException("중복된 닉네임 입니다.");
        }

        // 패스워드가 일치 하지 않을때
        if(!password.equals(passwordCheck)){
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호에 아이디 문자열 포함 여부 확인
        if(password.contains(username)){
            throw new NullPointerException("비밀번호에 아이디가 포함되어 있습니다.");
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, passwordCheck);
        userRepository.save(user);

    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }




}