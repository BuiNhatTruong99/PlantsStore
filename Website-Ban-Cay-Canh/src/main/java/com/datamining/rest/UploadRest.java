package com.datamining.rest;

import com.datamining.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.*;
import java.io.File;

@RestController
public class UploadRest {
    @Autowired
    UploadService upservice;

    @PostMapping("/api/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file,
                           @PathVariable("folder") String folder)
    {
        File saveFile = upservice.uploadFile(file,folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return node;
    }
}
