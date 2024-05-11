package com.efubtoy.team1.global.exception;

import lombok.Getter;

import javax.naming.AuthenticationException;

@Getter
public class JWTAuthenticationException extends AuthenticationException {
    public JWTAuthenticationException(String message) {
        super(message);
    }
}
