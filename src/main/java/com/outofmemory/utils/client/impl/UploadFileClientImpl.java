package com.outofmemory.utils.client.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.outofmemory.dto.FileDto;
import com.outofmemory.utils.client.UploadFileClient;
import com.outofmemory.utils.converer.FileConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@Log4j2
@AllArgsConstructor
public class UploadFileClientImpl implements UploadFileClient {
    private final FileConverter fileConverter;

    public FileDto uploadFile(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxxw3k4oc",
                "api_key", "293951996457269",
                "api_secret", "eGkH49mSbH8jOELJX8UqMzYUgfE"));
        Map params = ObjectUtils.asMap(
                "overwrite", true,
                "resource_type", "image"
        );
        try {
            Map upload = cloudinary.uploader().upload(fileConverter.convert(file), params);
            log.info(upload);
            return map(upload);
        } catch (IOException e) {
            log.error("Error while uploading a photo", e);
        }
        return null; // todo trow exception
    }

    private FileDto map(Map response) {
        FileDto dto = new FileDto();
        dto.setUrl((String) response.get("url"));
        return dto;
    }
}
