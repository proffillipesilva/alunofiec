package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.FcmRequest;
import com.fiec.alunofiec.business.models.dto.SendMessageRequest;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.services.IMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messaging")
public class MessagingController {

    @Autowired
    IMessagingService messagingService;

    @PostMapping("/register")
    public void registraFcmToken(@RequestBody FcmRequest fcmRequest, Authentication authentication) throws FirebaseMessagingException {
        User user = (User) authentication.getPrincipal();
        String fcmToken = fcmRequest.getToken();
        messagingService.registraFcmToken(user, fcmToken);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody SendMessageRequest sendMessageRequest) throws FirebaseMessagingException {
        messagingService.sendMessageToCourse(sendMessageRequest.getCursoId(), sendMessageRequest.getTitle(), sendMessageRequest.getMessage());
    }


}
