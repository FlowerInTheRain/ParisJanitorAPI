package fr.jypast.parisjanitorapi.domain.functionnal.exception;

public class EmailNotSendException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "Email was not send. Probably email doesn't exist.";

    public EmailNotSendException() {
        super(EXCEPTION_MESSAGE);
    }
}
