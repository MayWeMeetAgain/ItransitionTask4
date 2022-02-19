package com.annieryannel.userssecure.controllers;

import com.annieryannel.userssecure.DTOs.MessageDTO;
import com.annieryannel.userssecure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageDTO message) {
        userService.saveMessage(to, message.getFromUsername(), message.getMessage(), message.getTheme());
        boolean isActive = userService.isUserActive(userService.getUserByUsername(to));
        try {
            if (isActive) { simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);}
        } catch (MessagingException e) {}
    }
}
