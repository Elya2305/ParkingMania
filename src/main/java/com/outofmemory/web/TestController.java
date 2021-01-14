package com.outofmemory.web;

import com.outofmemory.dto.FileDto;
import com.outofmemory.utils.client.UploadFileClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class TestController {
    public UploadFileClient uploadFileClient;

    @PostMapping
    public FileDto test(@RequestParam("file") MultipartFile file) {
        return uploadFileClient.uploadFile(file);
    }
}
