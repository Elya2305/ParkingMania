package com.outofmemory.utils.client.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.outofmemory.dto.FileDto;
import com.outofmemory.excetion.UploadFileException;
import com.outofmemory.utils.client.UploadFileClient;
import com.outofmemory.utils.converer.FileConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
*
* class in Java 4 style (this library works without generics)
* */

@Component
@Log4j2
public class UploadFileClientImpl implements UploadFileClient {
    private final FileConverter fileConverter;
    @Value("${cloud.name}")
    private String CLOUD_NAME;
    @Value("${api.key}")
    private String API_KEY;
    @Value("${api.secret}")
    private String API_SECRET;

    public UploadFileClientImpl(FileConverter fileConverter) {
        this.fileConverter = fileConverter;
    }

    public FileDto uploadFile(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME, "api_key", API_KEY, "api_secret", API_SECRET));
        Map params = ObjectUtils.asMap("overwrite", true, "resource_type", "image");
        try {
            Map upload = cloudinary.uploader().upload(fileConverter.convert(file), params);
            log.info(upload);
            return map(upload);
        } catch (IOException e) {
            log.error("Error while uploading a photo", e);
            throw new UploadFileException("Sorry, we can't upload this file");
        }
    }

    private FileDto map(Map response) {
        FileDto dto = new FileDto();
        dto.setUrl((String) response.get("url"));
        return dto;
    }
}
