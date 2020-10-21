package com.forsrc.boot.ui.gwt.web.message;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @GetMapping(value = "/user")
    public Principal me(Principal user) {
        System.out.println("user --> " + user);
        return user;
    }

    @RequestMapping(value = "/test/1")
    public ResponseEntity<String> test1(HttpSession session, Principal user) {
        session.setAttribute("test", System.currentTimeMillis());
        Long test = (Long)session.getAttribute("test");

        return new ResponseEntity<>("1: " + session.getId() + "->" + test + "->" + user.getName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/test/2")
    public ResponseEntity<String> test2(HttpSession session, Principal user) {

        Long test = (Long)session.getAttribute("test");

        return new ResponseEntity<>("2: " + session.getId() + "->" + session.getAttribute("test") + "->" + user.getName(), HttpStatus.OK);
    }
}
