package com.forsrc.boot.authorization.web.test;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TestController {

    @Autowired
    private Cache<Serializable, Serializable> cache;

    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<Map<String, String>> get(UriComponentsBuilder ucBuilder) {
        Map<String, String> map = new HashMap<>();
        cache.put("/root/test", "time", new Date());
        map.put("test", "hello world");
        map.put("time", cache.get("/root/test",  "time").toString());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
