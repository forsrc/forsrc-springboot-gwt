package com.forsrc.boot.resource.web.plantuml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

@RestController
@RequestMapping(value = "/plantuml")
public class PlantUmlController {

    private static final Map<FileFormat, String> CONTENT_TYPE;
    static {
        Map<FileFormat, String> map = new HashMap<FileFormat, String>();
        map.put(FileFormat.PNG, "image/png");
        map.put(FileFormat.SVG, "image/svg+xml");
        map.put(FileFormat.EPS, "application/postscript");
        map.put(FileFormat.UTXT, "text/plain;charset=UTF-8");
        CONTENT_TYPE = Collections.unmodifiableMap(map);
    }

    @RequestMapping(value = "/{type}", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<byte[]> type(@PathVariable("type") String type, @RequestParam("uml") String uml,
            UriComponentsBuilder ucBuilder) throws IOException {
        SourceStringReader reader = new SourceStringReader(param(uml));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileFormat fileFormat = FileFormat.valueOf(type.toUpperCase());
        if (fileFormat == null) {
            fileFormat = FileFormat.PNG;
        }
        reader.outputImage(out, new FileFormatOption(FileFormat.SVG, false));
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_JPEG);
        headers.set(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE.get(fileFormat));
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/uml", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<byte[]> uml(@RequestParam("type") String type, @RequestParam("uml") String uml,
            UriComponentsBuilder ucBuilder) throws IOException {
        return type(type, uml, ucBuilder);
    }

    private String param(String uml) {
        return uml.replaceAll("(%0A)|(\\\\n)", "\n");
    }
}
