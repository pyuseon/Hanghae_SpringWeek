package com.example.boardlogin.controller;

import com.example.boardlogin.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor // Repository 와 service 둘다 불러와 !
@Controller
public class PageController {


    @GetMapping("/posts/detail")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            model.addAttribute("username", "");
        }else {
            model.addAttribute("username", userDetails.getUsername());
        };
        return "detail";
    }



    @GetMapping("/posting")
    public String posting(){

        return "posting";
    }


    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            model.addAttribute("username", "");
        }else {
            model.addAttribute("username", userDetails.getUsername());
        };
        return "index";
    }

    @GetMapping("/forbidden")
    public String forbidden(){

        return "forbidden";
    }



}