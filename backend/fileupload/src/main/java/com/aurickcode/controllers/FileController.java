package com.aurickcode.controllers;

import com.aurickcode.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("upload")
    public String fileupload(@RequestParam("file") MultipartFile file) {
        String downloadLink = fileService.fileupload(file);
        return downloadLink;
    }
}
