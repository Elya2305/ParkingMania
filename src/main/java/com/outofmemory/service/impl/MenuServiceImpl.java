package com.outofmemory.service.impl;

import com.outofmemory.dto.menu.MenuItem;
import com.outofmemory.dto.menu.MenuItemDto;
import com.outofmemory.service.MenuService;
import com.outofmemory.utils.security.AuthGateway;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public List<MenuItemDto> menu() {
        return Arrays.stream(MenuItem.values())
                .filter(o -> o.roleAccess().contains(AuthGateway.currentRole()))
                .map(this::map)
                .collect(Collectors.toList());
    }

    private MenuItemDto map(MenuItem source) {
        MenuItemDto destination = new MenuItemDto();
        destination.setMenuItem(source);
        destination.setName(source.getName());
        return destination;
    }
}
