package com.example.boardlogin.service;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.model.User;
import com.example.boardlogin.repository.UserRepository;
import com.example.boardlogin.vailidator.SignupValidator;
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
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new NullPointerException("중복된 닉네임 입니다.");
//        }

        if (findByUsername(username)) {
            throw new NullPointerException("중복된 닉네임 입니다.");
        }

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

//    public void Optional<User> getId(String username) {
//// 회원 ID 중복 확인
//        Optional<User> found = userRepository.findByUsername(username);
//        return found;
//    }


//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validatorResult = new HashMap<>();
//
//        for (FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }
//
//        return validatorResult;
//    }

}