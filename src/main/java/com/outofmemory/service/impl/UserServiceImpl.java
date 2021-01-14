package com.outofmemory.service.impl;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.User;
import com.outofmemory.repository.UserRepository;
import com.outofmemory.service.ComplainService;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ComplainService complainService;
    private final UserRepository userRepository;

    @Override
    public boolean addComplaining(ComplainDto dto) {
        ComplainInfo newComplaint = complainService.validateAndGetComplaint(dto);
        User user = getFromDb(dto.getUserUuid());
        user.addComplain(newComplaint);
        userRepository.save(user);
        return true;
    }

    private User getFromDb(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() -> new NoSuchElementException("Can't find user with uuid " + uuid));
    }
}
