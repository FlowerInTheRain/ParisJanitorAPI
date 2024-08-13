package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

    Optional<User> findByToken(UUID token);

    Optional<User> findByEmail(String email);


    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findByVerificationCode(String code);

    void deleteByToken(UUID token);

    User update(User o);

}
