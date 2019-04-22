package com.forsrc.boot.ui.gwt.web.message;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/user")
    public Principal me(Principal user) {
        System.out.println("user --> " + user);
        return user;
    }

}
