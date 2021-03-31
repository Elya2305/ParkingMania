package com.outofmemory.utils.converer.impl;

import com.outofmemory.exception.UploadFileException;
import com.outofmemory.utils.converer.FileConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class FileConverterImpl implements FileConverter {
    @Override
    public File convert(MultipartFile file) {
        try {
            File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            boolean newFile = convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            log.info("New file! Result {}", newFile);
            return convFile;
        } catch (IOException e) {
            log.error("Error while converting file", e);
            throw new UploadFileException("Sorry, we can't upload this file");
        }
    }
}
