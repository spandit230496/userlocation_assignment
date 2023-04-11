package com.userlocation.project.controller;

import com.userlocation.project.modal.UserLocation;
import com.userlocation.project.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")

public class ReaderController {
    @Autowired
    UserLocationService userLocationService;

    @GetMapping("/get_user/{N}")
    public List<UserLocation> getNearNUser(@PathVariable int N){
        return userLocationService.getNearUser(N);

    }
}
