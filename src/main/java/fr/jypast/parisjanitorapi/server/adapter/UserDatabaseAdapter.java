package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import fr.jypast.parisjanitorapi.server.mapper.UserEntityMapper;
import fr.jypast.parisjanitorapi.server.repository.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserPersistenceSpi {

    private final UserRepository repository;

    @Override
    @Transactional
    public Either<ApplicationError, User> save(User o) {
        return Try(() -> repository.save(UserEntityMapper.fromDomain(o)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Erreur lors de l'écriture en BDD", null, o, throwable))
                .map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return repository.findAll().stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByToken(UUID token) {
        return repository.findByToken(token).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByVerificationCode(String code) {
        return repository.findByVerificationCode(code).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByPasswordVerification(String code) {
        return repository.findByPasswordVerification(code).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByToken(UUID token) {
        repository.deleteByToken(token);
    }

    @Override
    @Transactional
    public User update(User o) {
        return UserEntityMapper.toDomain(repository.save(UserEntityMapper.fromDomain(o)));
    }
}
