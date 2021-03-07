package com.outofmemory.service.impl;

import com.outofmemory.dto.user.UserDto;
import com.outofmemory.dto.user.auth.RegResponseDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.entity.User;
import com.outofmemory.service.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public User map(RegResponseDto source) {
        User destination = new User();
        destination.setLocalId(source.getLocalId());
        destination.setEmail(source.getEmail());
        destination.setRole(User.Role.USER);
        destination.setStatus(User.Status.ACTIVE);
        return destination;
    }

    @Override
    public UserDto map(User source) {
        UserDto destination = new UserDto();
        destination.setEmail(source.getEmail());
        destination.setStatus(source.getStatus());
        destination.setLocalId(source.getLocalId());
        destination.setComplaintStatistic(map(source.getComplaints()));
        return destination;
    }

    private Map<ComplaintStatus, Long> map(List<ComplainInfo> complaints) {
        if (isNull(complaints)) {
            return Collections.emptyMap();
        }
        return complaints.stream().collect(
                Collectors.groupingBy(ComplainInfo::getStatus,
                        Collectors.counting()));
    }
}
