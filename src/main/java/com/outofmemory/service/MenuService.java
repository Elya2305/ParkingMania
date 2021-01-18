package com.outofmemory.service;

import com.outofmemory.dto.menu.MenuItem;
import com.outofmemory.dto.menu.MenuItemDto;

import java.util.List;

public interface MenuService {
    List<MenuItemDto> menu();
}
