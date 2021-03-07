package com.outofmemory.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

    @GetMapping("/alive")
    public boolean alive() {
        return true;
    }
}
