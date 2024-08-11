package fr.jypast.parisjanitorapi.domain.functionnal.exception;

public class DataNotSaveException extends RuntimeException {
    public static String EXCEPTION_MESSAGE = "An error occurred while saving into database";

    public DataNotSaveException() {
        super(EXCEPTION_MESSAGE);
    }
}
