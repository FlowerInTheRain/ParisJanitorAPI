package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import fr.jypast.parisjanitorapi.domain.port.in.user.UserFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserFinderService implements UserFinderApi {

    private final UserPersistenceSpi spi;

    @Override
    public List<User> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return spi.findByEmail(email);
    }


}
