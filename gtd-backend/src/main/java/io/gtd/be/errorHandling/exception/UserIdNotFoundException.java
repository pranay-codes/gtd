package io.gtd.be.errorHandling.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(String message) {
        super(String.format("UserId [%s] not found", message));
    }
}
