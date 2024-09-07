package com.main.janBnb.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 s3Client;


    @Value("${aws.s3.bucketName}")
    private String bucketName;


    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        s3Client.putObject(bucketName,fileName,file.getInputStream(),null);
        return s3Client.getUrl(bucketName, fileName).toString();
    }

    public String deleteImage(String fileName) {
        s3Client.deleteObject(bucketName,fileName);
        return "File Deleted Successfully";
    }
}
