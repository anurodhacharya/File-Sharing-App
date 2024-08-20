package com.aurickcode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.aurickcode.dao.FileSharingDAO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private AmazonS3 amazonS3;
    private FileSharingDAO fileSharingDAO;

    public FileService(FileSharingDAO fileSharingDAO) {
        this.fileSharingDAO = fileSharingDAO;
    }

    public String fileupload(MultipartFile file) {
        String name = file.getName();
        return fileSharingDAO.uploadFile(file);
    }
}
