package com.main.janBnb.controller;

import com.main.janBnb.payload.ImageDto;
import com.main.janBnb.service.ImageService;
import com.main.janBnb.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/main/image")
public class ImageController {

    private final S3Service s3Service;
    private final ImageService imageService;

    @Autowired
    public ImageController(S3Service s3Service, ImageService imageService) {
        this.s3Service = s3Service;
        this.imageService = imageService;
    }

    @PostMapping("/upload/property/{propertyId}")
    public ResponseEntity<?> uploadFile(
            MultipartFile file,
            @PathVariable String propertyId) {
        try {
             String fileUrl = s3Service.uploadFile(file);
             String fileName = file.getOriginalFilename();
            ImageDto imageDto = imageService.addImageForProperty(propertyId, fileUrl,fileName);
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<?> deleteImage(
            @PathVariable String imageId) {
         String msg = imageService.deleteImage(imageId);
         return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
