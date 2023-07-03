package com.companny.Oauthresourceserver.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/users")
    public String[] getUSer(){
        return new String[]{"blabla1","blala2","blabla3"};
    }
}
