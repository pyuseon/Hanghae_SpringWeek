package com.example.restaurant.service;

import com.example.restaurant.dto.SignupRequestDto;
import com.example.restaurant.model.User;
import com.example.restaurant.model.UserRoleEnum;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder; // password 암호화를 위한 코드
    private final UserRepository userRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        System.out.println( requestDto.getUsername());
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

//  사용자 role 확인하기
        UserRoleEnum role = UserRoleEnum.USER;
        // 사장님의 경우 이름 저장
        if(requestDto.isAdmin()){
            // 사장님의 경우 이름 저장
            role = UserRoleEnum.ADMIN;
            User user = new User(username, password, requestDto.getOwnername(),null, role);
            userRepository.save(user);
        }else{
            // 유저의 경우 닉네임 저장
            User user = new User(username, password,null, requestDto.getNickname(), role);
            userRepository.save(user);
        }


    }


}