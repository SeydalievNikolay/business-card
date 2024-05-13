package org.seydaliev.businesscard.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String string) {
        super(string);
    }
}
