package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.entities.User;

public interface IMessagingService {

    void registraFcmToken(User user, String fcmToken);
}
