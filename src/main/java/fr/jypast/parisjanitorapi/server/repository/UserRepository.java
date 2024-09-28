package fr.jypast.parisjanitorapi.server.repository;


import fr.jypast.parisjanitorapi.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByToken(UUID token);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    void deleteByToken(UUID token);

}
