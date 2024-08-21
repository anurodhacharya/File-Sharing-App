package com.aurickcode.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.aurickcode.dao.FileSharingDAO;
import com.aurickcode.entity.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class FileService {
    private AmazonS3 amazonS3;
    private FileSharingDAO fileSharingDAO;

    public FileService(FileSharingDAO fileSharingDAO, AmazonS3 amazonS3) {
        this.fileSharingDAO = fileSharingDAO;
        this.amazonS3 = amazonS3;
    }

    public URL fileupload(MultipartFile multipartFile) {
        try {
            String bucketName = "filesuploadcontainer";
            String keyName = "uploads/" + multipartFile.getOriginalFilename();
            File file = convertMultiPartToFile(multipartFile);

            amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file));
            URL url = amazonS3.getUrl(bucketName, keyName);
            FileEntity entity = new FileEntity(
                    multipartFile.getOriginalFilename(),
                    null,
                    null,
                    url
            );
            fileSharingDAO.uploadFile(entity);
            return url;
        } catch (IOException e) {
            System.out.println("Error");
        }
        return null;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
