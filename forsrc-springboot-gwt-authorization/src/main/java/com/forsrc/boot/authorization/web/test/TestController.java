package com.forsrc.boot.authorization.web.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TestController {
    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })

    public ResponseEntity<Map<String, String>> get(UriComponentsBuilder ucBuilder) {
        Map<String, String> map = new HashMap<>();
        map.put("test", "hello world");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
