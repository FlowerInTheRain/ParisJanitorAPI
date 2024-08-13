package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.AccountNotValidatedException;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.EmailAlreadyUsedException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserCheckerService {

    private final UserPersistenceSpi spi;

    public void emailIsAvailable(User user) {
        Optional<User> userWithSameEmail = spi.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent()) {
            throw new EmailAlreadyUsedException();
        }
    }

    public void accountIsActivated(User user) {
        if(!user.isActivated()) {
            throw new AccountNotValidatedException();
        }
    }
}
