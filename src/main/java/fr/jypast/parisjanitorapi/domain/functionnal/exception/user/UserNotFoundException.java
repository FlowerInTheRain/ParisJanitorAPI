package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class UserNotFoundException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }
}
