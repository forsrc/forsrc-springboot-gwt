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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

@RestController
@RequestMapping(value = "/plantuml")
public class PlantUmlController {

    @RequestMapping(value = "/svg", method = { RequestMethod.GET, RequestMethod.POST }, produces = { "image/svg+xml" })
    public ResponseEntity<byte[]> svg(@RequestParam("uml") String uml, UriComponentsBuilder ucBuilder)
            throws IOException {
        SourceStringReader reader = new SourceStringReader(uml);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        reader.outputImage(out, new FileFormatOption(FileFormat.SVG, false));
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/png", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.IMAGE_PNG_VALUE })
    public ResponseEntity<byte[]> png(@RequestParam("uml") String uml, UriComponentsBuilder ucBuilder)
            throws IOException {
        SourceStringReader reader = new SourceStringReader(uml);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        reader.outputImage(out, new FileFormatOption(FileFormat.PNG, false));
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/atxt", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
            MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<byte[]> txt(@RequestParam("uml") String uml, UriComponentsBuilder ucBuilder)
            throws IOException {
        SourceStringReader reader = new SourceStringReader(uml);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        reader.outputImage(out, new FileFormatOption(FileFormat.ATXT, false));
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}
