package com.aurickcode.controllers;

import com.aurickcode.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public URL fileupload(@RequestParam("file") MultipartFile file) {
        URL downloadLink = fileService.fileupload(file);
        return downloadLink;
    }
}
