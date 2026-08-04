package com.tyler.dev.firstapirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordTeste {
    @GetMapping("/")
    public String hello(){
        return "Olá eu sou o lay kon dev, e a minha API está a funcionar";
    }
}
