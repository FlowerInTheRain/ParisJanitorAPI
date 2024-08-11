package fr.jypast.parisjanitorapi.client.error;

import fr.jypast.parisjanitorapi.client.dto.ErrorDto;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.EmailNotSendException;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.UuidFormatNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException() {
        return new ErrorDto("La requête envoyée est invalide");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UuidFormatNotValidException.class)
    public ErrorDto handleUuidNotValidException() {
        return new ErrorDto(UuidFormatNotValidException.EXCEPTION_MESSAGE);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataNotSaveException.class)
    public ErrorDto handleDataNotSaveException() {
        return new ErrorDto(DataNotSaveException.EXCEPTION_MESSAGE);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailNotSendException.class)
    public ErrorDto handleEmailNotSendException() {
        return new ErrorDto(EmailNotSendException.EXCEPTION_MESSAGE);
    }

}
