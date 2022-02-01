package com.example.boardlogin.controller;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.model.User;
import com.example.boardlogin.security.UserDetailsImpl;
import com.example.boardlogin.service.KakaoUserService;
import com.example.boardlogin.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    // 카카오 로그인
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

    //로그인 사용자 정보 받아오기
    @GetMapping("/user/info")
    public User getUserinfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUser().getUsername();
        System.out.println(userId);
        System.out.println(username);
        return new User(userId, username);
    }


}

