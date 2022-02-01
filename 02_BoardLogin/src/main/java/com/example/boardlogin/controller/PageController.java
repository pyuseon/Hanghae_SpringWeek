package com.example.boardlogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@Controller
public class PageController {

    @GetMapping("/posts/detail")
    public String detail(){
        return "detail";
    }

    @GetMapping("/posting")
    public String posting(){
        return "posting";
    }

    @GetMapping("/")
    public String home(){
//    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }
}