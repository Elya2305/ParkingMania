package com.outofmemory.service.impl;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.User;
import com.outofmemory.excetion.ValidationException;
import com.outofmemory.repository.ComplaintRepository;
import com.outofmemory.service.ComplainService;
import com.outofmemory.utils.client.UploadFileClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

// todo add: update / delete (if status is new)
@Service
@AllArgsConstructor
public class ComplainServiceImpl implements ComplainService {
    private final UploadFileClient uploadFileClient;
    private final ComplaintRepository complaintRepository;


    @Override
    public boolean add(ComplainDto dto) {
        validate(dto);
        ComplainInfo newComplaint = map(dto, new ComplainInfo());
        complaintRepository.save(newComplaint);
        return true;
    }

    @Override
    public List<ComplainDto> all() {
        return complaintRepository.findAll()
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    private ComplainInfo map(ComplainDto resource, ComplainInfo destination) {
        destination.setId(resource.getId());
        destination.setStatus(ComplainInfo.Status.NEW);
        destination.setAutoNumber(resource.getAutoNumber());
        destination.setDescription(resource.getDescription());
//        destination.setPhotoUrl(uploadFileClient.uploadFile(resource.getPhoto()).getUrl());
        return destination;
    }

    private ComplainDto map(ComplainInfo resource) {
        ComplainDto destination = new ComplainDto();
        destination.setId(resource.getId());
        destination.setAutoNumber(resource.getAutoNumber());
        destination.setDescription(resource.getDescription());
        return destination;
    }

    private void validate(ComplainDto dto) {
        // todo
    }
}
