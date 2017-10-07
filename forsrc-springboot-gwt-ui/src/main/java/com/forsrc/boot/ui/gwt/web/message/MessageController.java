package com.forsrc.boot.ui.gwt.web.message;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @RequestMapping("/")
    String home(Model model) {
        return "index";
    }

    public static class Message {
        public String text;
        public String username;
        public LocalDateTime createdAt;
    }
}
