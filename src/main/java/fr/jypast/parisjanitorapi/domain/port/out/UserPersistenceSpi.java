package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

    Optional<User> findByToken(UUID token);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String email, String password);
    void deleteByToken(UUID token);
    User update(User o);

}
