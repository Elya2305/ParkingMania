package com.outofmemory.utils.client;

import com.outofmemory.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileClient {
    FileDto uploadFile(MultipartFile file);
}
