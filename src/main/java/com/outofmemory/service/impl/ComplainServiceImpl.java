package com.outofmemory.service.impl;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.exception.ValidationException;
import com.outofmemory.repository.ComplaintRepository;
import com.outofmemory.service.ComplainService;
import com.outofmemory.service.GeoService;
import com.outofmemory.utils.client.UploadFileClient;
import com.outofmemory.utils.security.AuthGateway;
import com.outofmemory.utils.security.PermissionChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ComplainServiceImpl implements ComplainService {
    private final UploadFileClient uploadFileClient;
    private final ComplaintRepository complaintRepository;
    private final GeoService geoService;


    @Override
    public boolean add(ComplainDto dto) {
        ComplainInfo newComplaint = map(dto, new ComplainInfo());
        complaintRepository.save(newComplaint);
        return true;
    }

    @Override
    public boolean update(ComplainDto dto) {
        ComplainInfo entity = getFromDb(dto.getId());
        validateUpdate(entity);
        map(dto, entity);
        complaintRepository.save(entity);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        validateDelete(id);
        complaintRepository.delete(id);
        return true;
    }

    @Override
    public List<ComplainDto> allOfCurrent(ComplaintStatus status) {
        return complaintRepository.findAllByOwnerIdAndStatus(
                AuthGateway.currentUserId(), status)
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplainDto> allByStatus(ComplaintStatus status){
        PermissionChecker.isAdmin(); //todo annotation
        return complaintRepository.findAllByStatus(status)
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    private ComplainInfo getFromDb(Integer id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find complain by id " + id));
    }

    // todo add validation of autonumber
    // todo add photo
    private ComplainInfo map(ComplainDto resource, ComplainInfo destination) {
        destination.setId(resource.getId());
        destination.setAutoNumber(resource.getAutoNumber());
        destination.setDescription(resource.getDescription());
        destination.setLocation(resource.getLocation());
        destination.setLocationAddress(geoService.address(resource.getLocation()));
        return destination;
    }

    private ComplainDto map(ComplainInfo resource) {
        ComplainDto destination = new ComplainDto();
        destination.setId(resource.getId());
        destination.setAutoNumber(resource.getAutoNumber());
        destination.setDescription(resource.getDescription());
        destination.setAddress(resource.getLocationAddress());
        destination.setLocation(resource.getLocation());
        destination.setStatus(resource.getStatus());
        return destination;
    }

    private void validateUpdate(ComplainInfo entity) {
        if (!entity.getStatus().equals(ComplaintStatus.NEW)) {
            throw new ValidationException("Sorry, you can't update complaint with status " + entity.getStatus());
        }
    }

    private void validateDelete(Integer id) {
        ComplaintStatus status = complaintRepository.getStatusById(id);
        if (isNull(status) || ComplaintStatus.NEW != status) {
            throw new ValidationException("Sorry, you can't delete complaint with status " + status);
        }
    }
}
