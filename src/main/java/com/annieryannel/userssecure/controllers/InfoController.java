package com.annieryannel.userssecure.controllers;

import com.annieryannel.userssecure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public String getCurrentUser(Model model) {
        return userService.getCurrentUser().getUsername();
    }

//    @PostMapping("/saveMessage")
//    public void saveMessage() {
//        userService.saveMessage();
//    }
}
