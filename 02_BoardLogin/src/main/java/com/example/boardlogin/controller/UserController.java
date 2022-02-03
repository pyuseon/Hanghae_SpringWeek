package com.example.boardlogin.controller;

import com.example.boardlogin.dto.SignupRequestDto;
import com.example.boardlogin.security.UserDetailsImpl;
import com.example.boardlogin.service.KakaoUserService;
import com.example.boardlogin.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            model.addAttribute("username", "");
        }else {
            model.addAttribute("username", userDetails.getUsername());
        };
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
//    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            // 회원가입 실패시, 입력 데이터를 유지
//            model.addAttribute("requestDto", requestDto);
//            // 유효성 통과 못한 필드와 메시지를 핸들링
//            Map<String, String> validatorResult = userService.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//            return "signup";
//        }
    public String registerUser(@Valid SignupRequestDto requestDto, Model model) {
        try{ userService.registerUser(requestDto);
        }catch (NullPointerException error){
            model.addAttribute("error", error.getMessage());
            return "signup";
        }
//        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }
    // 카카오 로그인
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

}

