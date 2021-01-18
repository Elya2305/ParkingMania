package com.outofmemory.dto.user;

import com.outofmemory.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String localId;
    private int totalComplaint;
    private User.Status status;
}
