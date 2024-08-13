package fr.jypast.parisjanitorapi.domain.port.in.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFinderApi {

    List<User> findAll();

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

}
