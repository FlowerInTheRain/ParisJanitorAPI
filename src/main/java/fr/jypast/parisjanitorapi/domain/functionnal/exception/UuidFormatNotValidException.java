package fr.jypast.parisjanitorapi.domain.functionnal.exception;

public class UuidFormatNotValidException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "UUID is not valid";

    public UuidFormatNotValidException() {
        super(EXCEPTION_MESSAGE);
    }
}
