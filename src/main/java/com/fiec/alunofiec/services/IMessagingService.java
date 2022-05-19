package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.entities.User;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface IMessagingService {

    void registraFcmToken(User user, String fcmToken) throws FirebaseMessagingException;

    void sendMessageToCourse(String cursoId, String title, String message) throws FirebaseMessagingException;

}
