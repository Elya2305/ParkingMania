package com.outofmemory.dto.user;

import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.entity.User;
import lombok.Data;

import java.util.Map;

@Data
public class UserDto {
    private String email;
    private String localId;
    private Map<ComplaintStatus, Long> complaintStatistic;
    private User.Status status;
    private User.Role role;
}
