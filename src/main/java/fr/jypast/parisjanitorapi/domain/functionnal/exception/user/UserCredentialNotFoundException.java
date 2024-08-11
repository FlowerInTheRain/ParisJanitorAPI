package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class UserCredentialNotFoundException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "No user matches the provided username and password.";

    public UserCredentialNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}
