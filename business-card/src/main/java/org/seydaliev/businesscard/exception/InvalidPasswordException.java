package org.seydaliev.businesscard.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String string) {
        super(string);
    }
}
