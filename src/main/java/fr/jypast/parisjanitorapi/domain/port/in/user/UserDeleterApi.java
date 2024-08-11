package fr.jypast.parisjanitorapi.domain.port.in.user;

import java.util.UUID;

public interface UserDeleterApi {

    void deleteById(UUID id);

    void deleteByToken(UUID token);

}
