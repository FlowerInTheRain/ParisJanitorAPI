package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.port.in.user.UserDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserDeleterService implements UserDeleterApi {

    private final UserPersistenceSpi spi;

    @Override
    public void deleteById(UUID id) {
        spi.deleteById(id);
    }

    @Override
    public void deleteByToken(UUID token) {
        spi.deleteByToken(token);
    }
}
