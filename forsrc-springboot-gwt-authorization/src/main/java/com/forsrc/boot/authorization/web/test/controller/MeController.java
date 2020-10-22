package com.forsrc.boot.authorization.web.test.controller;


import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {


    private static final Logger logger = LoggerFactory.getLogger(MeController.class);

    @RequestMapping(value = "/me")
    @PreAuthorize("isAuthenticated()")
    public Principal me(Principal user) {
        System.out.println("user --> " + user);
        return user;
    }


}
