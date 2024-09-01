package fr.jypast.parisjanitorapi.domain.port.in.property;

import java.util.UUID;

public interface PropertyDeleterApi {
    void deleteById(UUID id);

}

