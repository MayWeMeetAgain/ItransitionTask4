package com.annieryannel.userssecure.controllers;

import com.annieryannel.userssecure.models.MessageModel;
import com.annieryannel.userssecure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @GetMapping("/messages")
//    public String getMessage(Model model) {
//        model.addAttribute("username", userService.getCurrentUser().getUsername());
//        return "home";
//    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + "to: " + to);

        boolean isActive = userService.isUserActive(userService.getUserByUsername(to));
        try {
        if (isActive) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        } } catch (MessagingException e) {}
    }
}
