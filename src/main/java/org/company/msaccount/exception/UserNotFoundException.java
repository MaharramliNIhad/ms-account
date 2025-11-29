package org.company.msaccount.exception;

public class UserNotFoundException extends RuntimeException {
public UserNotFoundException(String message) {
    super(message);
}
}
