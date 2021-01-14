package com.outofmemory.utils.converer.impl;

import com.outofmemory.excetion.UploadFileException;
import com.outofmemory.utils.converer.FileConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Log4j2
public class FileConverterImpl implements FileConverter {
    @Override
    public File convert(MultipartFile file) {
        try {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            log.error("AAAAA", e);
            throw new UploadFileException();
        }
    }
}
