package com.fiec.alunofiec.business.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseAuthRequest {
    private String email;
    private String password;
    private boolean returnSecureToken;
}