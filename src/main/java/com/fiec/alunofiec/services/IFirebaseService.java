package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.dto.FirebaseAuthRequest;

public interface IFirebaseService {
    void register(FirebaseAuthRequest firebaseAuthRequest) throws Exception;
    void login(FirebaseAuthRequest firebaseAuthRequest) throws Exception;
}
