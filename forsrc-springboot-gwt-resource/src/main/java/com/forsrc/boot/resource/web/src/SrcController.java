package com.forsrc.boot.resource.web.src;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/src")
public class SrcController {

    @GetMapping("/list")
    public ResponseEntity<Object> list(@RequestParam("filename") String filename) {
        String name = filename;
        try {
            name = URLDecoder.decode(filename, "UTF-8");
            name = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            name = filename;
        }
        File file = new File(name);
        Map<String, Object> map = new HashMap<>(5);
        map.put("name", name);
        map.put("exists", file.exists());
        map.put("isFile", file.exists() ? file.isFile() : false);
        map.put("length", String.valueOf(file.length()));
        map.put("lastModified", String.valueOf(file.lastModified()));
        map.put("list", null);
        if (!file.exists()) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        if (file.isFile()) {
            try {
                map.put("text", FileUtils.readFileToString(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        File[] files = file.listFiles();
        List<Map<String, Object>> list = new ArrayList<>(files.length);
        Arrays.asList(files).forEach(f -> {
            Map<String, Object> fileMap = new HashMap<>();
            fileMap.put("name", f.getName());
            fileMap.put("isFile", f.isFile());
            fileMap.put("exists", f.exists());
            fileMap.put("length", f.isFile() ? String.valueOf(f.length()) : "0");
            fileMap.put("lastModified", String.valueOf(f.lastModified()));
            list.add(fileMap);
        });

        map.put("list", list);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
