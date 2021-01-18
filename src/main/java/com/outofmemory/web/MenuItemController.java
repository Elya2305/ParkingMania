package com.outofmemory.web;

import com.outofmemory.dto.menu.MenuItemDto;
import com.outofmemory.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuItemController {
    private final MenuService menuService;

    @GetMapping
    public List<MenuItemDto> menu() {
        log.info("Request on getting menu");
        List<MenuItemDto> menu = menuService.menu();
        log.info("Response on getting menu - {}", menu);
        return menu;
    }
}
