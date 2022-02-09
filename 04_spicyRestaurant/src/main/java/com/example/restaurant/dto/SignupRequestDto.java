package com.example.restaurant.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String ownername;
    private boolean admin = false;
}
