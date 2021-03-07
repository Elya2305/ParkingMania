package com.outofmemory.service.impl;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.exception.ValidationException;
import com.outofmemory.repository.ComplaintRepository;
import com.outofmemory.service.ComplainService;
import com.outofmemory.service.GeoService;
import com.outofmemory.utils.PagesUtility;
import com.outofmemory.utils.api.PageDto;
import com.outofmemory.utils.client.UploadFileClient;
import com.outofmemory.utils.security.AuthGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        complaintRepository.deleteById(id);
        return true;
    }

    @Override
    public PageDto<List<ComplainDto>> allOfCurrent(ComplaintStatus status, int page, int pageSize) {
        return complaintPage(status, page, pageSize, true);

    }

    @Override
    public PageDto<List<ComplainDto>> allByStatus(ComplaintStatus status, int page, int pageSize) {
        return complaintPage(status, page, pageSize, false);
    }

    private PageDto<List<ComplainDto>> complaintPage(ComplaintStatus status, int page, int pageSize, boolean current) {
        Pageable pageable = getPageableSortedByDate(page, pageSize);
        Page<ComplainInfo> result = isNull(status)
                ? getPageableOrderedByDate(pageable, current)
                : getPageableByStatusOrderedByDate(status, pageable, current);

        return PageDto.of(map(result.getContent()), result.getTotalPages(), page);
    }

    private ComplainInfo getFromDb(Integer id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find complain by id " + id));
    }

    private Page<ComplainInfo> getPageableByStatusOrderedByDate(ComplaintStatus status, Pageable pageable, boolean current) {
        return current ? complaintRepository.findAllByOwnerIdAndStatus(
                AuthGateway.currentUserId(), status, pageable)
                : complaintRepository.findAllByStatus(status, pageable);
    }

    private Page<ComplainInfo> getPageableOrderedByDate(Pageable pageable, boolean current) {
        return current ? complaintRepository.findAllByOwnerId(
                AuthGateway.currentUserId(), pageable)
                : complaintRepository.findAll(pageable);
    }

    private Pageable getPageableSortedByDate(int page, int pageSize) {
        return PagesUtility.createSortPageRequest(page, pageSize, PagesUtility.SortOrder.DESC, "dateCreated");
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

    private List<ComplainDto> map(List<ComplainInfo> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
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
