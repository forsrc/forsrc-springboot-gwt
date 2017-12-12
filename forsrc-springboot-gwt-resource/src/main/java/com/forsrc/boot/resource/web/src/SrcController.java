package com.forsrc.boot.resource.web.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        File file = new File(filename);
        if (!file.exists()) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("name", filename);
            map.put("exists", false);
            map.put("length", file.length() + "");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        if (file.isFile()) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("name", filename);
            map.put("isFile", file.isFile());
            map.put("length", file.length());
            map.put("lastModified", file.lastModified());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        File[] files = file.listFiles();
        List<Map<String, Object>> list = new ArrayList<>(files.length);
        Arrays.asList(files).forEach(f -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", f.getName());
            map.put("isFile", f.isFile() );
            map.put("length", f.isFile() ? f.length() : 0);
            map.put("lastModified", f.lastModified());
            list.add(map);
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
