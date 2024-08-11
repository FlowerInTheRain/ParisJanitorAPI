package fr.jypast.parisjanitorapi.domain.functionnal.service;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.TokenNotValidException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class TokenControllerService {

    private final UserPersistenceSpi spi;

    public User getUserByToken(UUID token) {
        return spi.findByToken(token)
                .orElseThrow(TokenNotValidException::new);
    }

    public User updateToken(User user) {
        User updatedUser = user.withToken(UUID.randomUUID())
                .withTokenDate(LocalDate.now());
        return spi.save(updatedUser)
                .getOrElseThrow(DataNotSaveException::new);
    }

}
