package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class AccountAlreadyValidatedException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "You have already validated your account";

    public AccountAlreadyValidatedException() {
        super(EXCEPTION_MESSAGE);
    }
}
