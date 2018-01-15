package com.forsrc.boot.authorization.web.test.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forsrc.boot.authorization.config.InfinispanConfig;


@RestController
public class TestController {

    @Autowired
    private EmbeddedCacheManager cacheManager;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<Map<String, String>> get(UriComponentsBuilder ucBuilder) {
        Map<String, String> map = new HashMap<>();
        Cache<String, String> cache = cacheManager.getCache(InfinispanConfig.CACHE_NAME);
        cache.put("time", new Date().toString());
        map.put("test", "hello world");
        map.put("time", cache.get("time"));

        cacheManager.executor().submitConsumer(localManager -> {
            Cache<String, String> c = localManager.getCache(InfinispanConfig.CACHE_NAME);
            System.out.println("--> " + c.get("time"));
            return c.get("time");
         }, (address, value, throwable) -> {
             if (throwable != null) {
                 logger.error("Cache startup encountered exception on node " + address, value);
             }
         }).join();

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
