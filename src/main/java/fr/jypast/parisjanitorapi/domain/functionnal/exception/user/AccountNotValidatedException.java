package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class AccountNotValidatedException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "You have not validated your account";

    public AccountNotValidatedException() {
        super(EXCEPTION_MESSAGE);
    }
}
