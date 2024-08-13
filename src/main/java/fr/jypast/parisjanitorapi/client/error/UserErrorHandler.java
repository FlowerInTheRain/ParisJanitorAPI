package fr.jypast.parisjanitorapi.client.error;

import fr.jypast.parisjanitorapi.client.dto.ErrorDto;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccountNotValidatedException.class)
    public ErrorDto handleUserNotValidatedException() {
        return new ErrorDto(AccountNotValidatedException.EXCEPTION_MESSAGE);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ErrorDto handleEmailAlreadyUsedException() {
        return new ErrorDto(EmailAlreadyUsedException.EXCEPTION_MESSAGE);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenNotValidException.class)
    public ErrorDto handleTokenNotValidException() {
        return new ErrorDto(TokenNotValidException.EXCEPTION_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserCredentialNotFoundException.class)
    public ErrorDto handleUserCredentialNotFoundException() {
        return new ErrorDto(UserCredentialNotFoundException.EXCEPTION_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDto handleUserNotFoundException() {
        return new ErrorDto(UserNotFoundException.EXCEPTION_MESSAGE);
    }
}
