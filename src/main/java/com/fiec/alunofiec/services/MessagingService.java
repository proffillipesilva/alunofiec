package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService implements IMessagingService{
    @Autowired
    IUserRepositorio userRepositorio;

    @Autowired
    IAlunoRepositorio alunoRepositorio;

    @Override
    public void registraFcmToken(User user, String fcmToken) throws FirebaseMessagingException {
        user.setFcmToken(fcmToken);
        userRepositorio.save(user);
        //if(FiecRoles.ROLE_USER.equals(user.getRole())) {
        Aluno aluno = alunoRepositorio.findByUser(user).orElseThrow();
        FirebaseMessaging.getInstance().subscribeToTopic(List.of(fcmToken), aluno.getCurso());
        //}
    }

    public void sendMessageToCourse(String cursoId, String title, String message)
            throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().send(getMessage(cursoId,title,message));

    }

    private Message getMessage(String cursoId, String title, String message){
        return Message.builder().setNotification(
                        Notification.builder().setTitle(title)
                                .setBody(message)
                                .build()
                ).setTopic(cursoId)
                .build();
    }
}
