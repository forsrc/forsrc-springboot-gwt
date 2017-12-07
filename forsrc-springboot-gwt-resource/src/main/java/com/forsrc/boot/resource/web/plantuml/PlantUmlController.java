package com.forsrc.boot.resource.web.plantuml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

@RestController
@RequestMapping(value = "/plantuml")
public class PlantUmlController {

    @RequestMapping(value = "/svg/{uml}", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<byte[]> svg(@PathVariable("uml") String uml, UriComponentsBuilder ucBuilder) throws IOException {
        SourceStringReader reader = new SourceStringReader(uml);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        reader.outputImage(out, new FileFormatOption(FileFormat.SVG, false));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}
