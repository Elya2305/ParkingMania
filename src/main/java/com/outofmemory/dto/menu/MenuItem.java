package com.outofmemory.dto.menu;

import com.outofmemory.entity.User;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum MenuItem {
    PROFILE("Profile"),
    HISTORY_COMPLAINTS("History of complaints"),
    NEW_COMPLAINTS("List of complaints"),
    LIST_USERS("List of user") {
        @Override
        public List<User.Role> roleAccess() {
            return Collections.singletonList(User.Role.ADMIN);
        }
    };

    private final String name;

    MenuItem(String name) {
        this.name = name;
    }

    public List<User.Role> roleAccess() {
        return Arrays.asList(User.Role.values());
    };
}
