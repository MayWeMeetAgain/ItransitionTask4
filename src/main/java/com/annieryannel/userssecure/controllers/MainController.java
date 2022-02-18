package com.annieryannel.userssecure.controllers;

import com.annieryannel.userssecure.service.UserService;
import com.annieryannel.userssecure.utils.actions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home_base(Model model) {
        model.addAttribute("users", userService.loadAllUsers());
        return "home";
    }

    @PostMapping("/")
    public String userAction(@RequestParam(defaultValue = "", required = false) List<String> users, @RequestParam String action) {
        Actions.valueOf(action).makeAction().execute(userService, users);
        return redirectCurrentUser();
    }

    public String redirectCurrentUser() {
        if(!userService.isUserActive(userService.getCurrentUser())) {
            return "redirect:/login?logout";
        }
        return "redirect:/";
    }
}
