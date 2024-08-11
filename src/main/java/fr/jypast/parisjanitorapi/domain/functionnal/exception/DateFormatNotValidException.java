package fr.jypast.parisjanitorapi.domain.functionnal.exception;

public class DateFormatNotValidException extends RuntimeException{
    public static String EXCEPTION_MESSAGE = "Date format is not valid, don't respect ISO 8601 (YYYY-MM-DD).";

    public DateFormatNotValidException() {
        super(EXCEPTION_MESSAGE);
    }
}
