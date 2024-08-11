package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.AccountAlreadyValidatedException;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.UserNotFoundException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserVerifierApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserVerifierService implements UserVerifierApi {

    private final UserPersistenceSpi spi;

    @Override
    public boolean verify(String email, String code) {
        User user = spi.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (user.isActivated()) {
            throw new AccountAlreadyValidatedException();
        }

        if (user.getVerificationCode().equals(code)) {
            spi.save(user
                    .withVerificationCode(null)
                    .withActivated(true));
            return true;
        }

        return false;

    }


}
