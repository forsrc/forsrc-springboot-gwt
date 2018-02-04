package com.forsrc.boot.ui.gwt.web.message;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @RequestMapping("/")
    String home(Model model) {
        return "index";
    }

    @RequestMapping("/me")
    @ResponseBody
    public Principal me(Principal principal) {
        return principal;
    }

    public static class Message {
        public String text;
        public String username;
        public LocalDateTime createdAt;
    }
}
