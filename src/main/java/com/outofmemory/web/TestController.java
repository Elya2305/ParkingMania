package com.outofmemory.web;

import com.outofmemory.dto.FileDto;
import com.outofmemory.dto.geo.GeoResponseDto;
import com.outofmemory.entity.User;
import com.outofmemory.utils.access_check.RolesHaveAccess;
import com.outofmemory.utils.client.GeocodingClient;
import com.outofmemory.utils.client.UploadFileClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
// todo remove
@RequestMapping("/test")
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

    @GetMapping("/3")
    @RolesHaveAccess(restrict = User.Role.ADMIN)
    public boolean test3() {
        return true;
    }
}
