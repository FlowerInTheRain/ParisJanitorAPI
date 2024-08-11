package fr.jypast.parisjanitorapi.client.validator;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.UuidFormatNotValidException;

import java.util.UUID;

public interface UuidValidator {

    static UUID validate(String id) {
        try {
            return UUID.fromString(id);
        }
        catch(Exception e) {
            throw new UuidFormatNotValidException();
        }
    }

}
