package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import io.vavr.control.Either;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersistenceSpi<T, ID> {

    Either<ApplicationError, T> save(T o);

    List<T> findAll();

    Optional<T> findById(ID id);

    void deleteById(UUID id);

}
