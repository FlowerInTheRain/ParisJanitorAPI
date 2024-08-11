package fr.jypast.parisjanitorapi.domain.port.in.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.UUID;

public interface UserCreatorApi {

    User create(User user);
    User createWithId(User user, UUID id);

}
