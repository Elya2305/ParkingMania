package com.outofmemory.service.impl;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.excetion.ValidationException;
import com.outofmemory.service.ComplainService;
import com.outofmemory.utils.client.UploadFileClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ComplainServiceImpl implements ComplainService {
    private final UploadFileClient uploadFileClient;

    @Override
    public ComplainInfo validateAndGetComplaint(ComplainDto dto) {
        validate(dto);
        return map(dto);
    }

    private void validate(ComplainDto dto) {
        if (isNull(dto) || isNull(dto.getUserUuid())) {
            throw new ValidationException("User uuid can't be empty");
        }
        if (isNull(dto.getPhoto())) {
            throw new ValidationException("Photo can't be empty");
        }
    }

    private ComplainInfo map(ComplainDto resource) {
        ComplainInfo destination = new ComplainInfo();
        destination.setStatus(ComplainInfo.Status.NEW);
        destination.setAutoNumber(resource.getAutoNumber());
        destination.setDescription(resource.getDescription());
        destination.setPhotoUrl(uploadFileClient.uploadFile(resource.getPhoto()).getUrl());
        return destination;
    }
}
