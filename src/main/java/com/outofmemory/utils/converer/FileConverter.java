package com.outofmemory.utils.converer;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileConverter {
    File convert(MultipartFile file);
}
