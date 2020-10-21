package com.forsrc.boot.ui.gwt.web.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class TestController {

    @GetMapping(value = "/test")
    public String me(Principal user) {
        System.out.println("user --> " + user);
        return "test";
    }


}
