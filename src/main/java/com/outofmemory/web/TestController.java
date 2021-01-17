package com.outofmemory.web;

import com.outofmemory.dto.FileDto;
import com.outofmemory.dto.geo.GeoResponseDto;
import com.outofmemory.utils.client.GeocodingClient;
import com.outofmemory.utils.client.UploadFileClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class TestController {
    private final UploadFileClient uploadFileClient;
    private final GeocodingClient geocodingClient;

    @PostMapping("/1")
    public FileDto test(@RequestParam("file") MultipartFile file) {
        return uploadFileClient.uploadFile(file);
    }

    @GetMapping("/2")
    public GeoResponseDto test2() {
        return geocodingClient.decode(50.44994277906307, 30.522923429465568);
    }
}
