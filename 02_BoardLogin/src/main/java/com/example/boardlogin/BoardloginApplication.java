package com.example.boardlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BoardloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardloginApplication.class, args);
    }

}
