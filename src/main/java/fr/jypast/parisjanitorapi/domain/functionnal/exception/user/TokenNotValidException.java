package fr.jypast.parisjanitorapi.domain.functionnal.exception.user;

public class TokenNotValidException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "Token is invalid";

    public TokenNotValidException() {
        super(EXCEPTION_MESSAGE);
    }
}
