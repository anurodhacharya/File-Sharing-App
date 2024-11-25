package com.aurickcode.controllers;

import com.aurickcode.dto.FileDownloadDetails;
import com.aurickcode.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.URL;

@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public String fileupload(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                            @RequestParam("timer") String timer)
    {
        String downloadLink = fileService.fileupload(file);
        return downloadLink;
//        return "Posted";
    }

    @PostMapping("download")
    public ResponseEntity<InputStreamResource> filedownload(@RequestBody FileDownloadDetails fileDownloadDetails) throws FileNotFoundException {
        return fileService.filedownload(fileDownloadDetails);
    }
}
