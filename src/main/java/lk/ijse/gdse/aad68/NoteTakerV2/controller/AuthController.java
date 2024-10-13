package lk.ijse.gdse.aad68.NoteTakerV2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @PostMapping(value = "signin")
    public String signIn(){
        return "signIn";
    }

    @PostMapping(value = "signup")
    public String signUp(){
        return "signUp";
    }

}
