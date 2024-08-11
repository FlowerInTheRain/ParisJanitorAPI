package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class EmailAlreadyUsedException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "Email is already in use";

    public EmailAlreadyUsedException() {
        super(EXCEPTION_MESSAGE);
    }
}
