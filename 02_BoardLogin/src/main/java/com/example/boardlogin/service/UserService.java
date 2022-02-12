package com.example.boardlogin.service;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.model.User;
import com.example.boardlogin.repository.UserRepository;
import com.example.boardlogin.validator.SignupValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();


        // Repository에 접근은 Service에서만 할수 있나요? 따로 validation에서 repository에 접근하려 하였으나 실패하였습니다.
        if (findByUsername(username)) {
            throw new NullPointerException("중복된 닉네임 입니다.");
        }


        // 유효성 검사
        SignupValidator.validateSignupInput(username, password, passwordCheck);


        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, passwordCheck);
        userRepository.save(user);

        return user;
    }

    public boolean findByUsername(String username) {
        return  userRepository.findByUsername(username).isPresent();
    }


}