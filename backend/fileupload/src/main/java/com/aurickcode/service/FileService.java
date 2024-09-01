package com.aurickcode.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.aurickcode.dao.FileSharingDAO;
import com.aurickcode.dto.FileDownloadDetails;
import com.aurickcode.entity.FileEntity;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {
    private AmazonS3 amazonS3;
    private FileSharingDAO fileSharingDAO;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public FileService(FileSharingDAO fileSharingDAO, AmazonS3 amazonS3) {
        this.fileSharingDAO = fileSharingDAO;
        this.amazonS3 = amazonS3;
    }

    public String fileupload(MultipartFile multipartFile) {
        try {
            String bucketName = "filesuploadcontainer";
            String keyName = "uploads/" + multipartFile.getOriginalFilename();
            File file = convertMultiPartToFile(multipartFile);

            String id = UUID.randomUUID().toString().substring(0, 8);
            amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file));
            URL s3Url = amazonS3.getUrl(bucketName, keyName);
            FileEntity entity = new FileEntity(
                    id,
                    LocalDateTime.now(),
                    null,
                    null,
                    s3Url
            );
            FileEntity fileEntity = fileSharingDAO.uploadFile(entity);
            String downloadUrl = "https://filesharingapp/" + id;;
            rabbitTemplate.convertAndSend("message_exchange", "message_routingKey", "File uploaded at: " + downloadUrl);
            return downloadUrl;
        } catch (IOException e) {
            System.out.println("Error");
        }
        return "error";
    }

    public ResponseEntity<InputStreamResource> filedownload(FileDownloadDetails fileDownloadDetails) throws FileNotFoundException {
        URI uri = URI.create(fileDownloadDetails.downloadLink());
        String fileId = uri.getPath().substring(1);
        FileEntity file = fileSharingDAO.downloadFile(fileId).orElseThrow(() -> new FileNotFoundException("Not Found"));
        URL fileDownloadLink = file.getDownload_link();
        String host = fileDownloadLink.getHost();
        String bucketName = host.split("\\.")[0];

        String fullPath = fileDownloadLink.getPath();
        String key = fullPath.substring(1);

        S3Object s3Object = amazonS3.getObject(bucketName, key);
        InputStream inputStream = s3Object.getObjectContent();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + key.substring(key.lastIndexOf("/") + 1) + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok().headers(headers).contentLength(s3Object.getObjectMetadata().getContentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(inputStream));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
