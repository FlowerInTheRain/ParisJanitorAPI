package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.UserCredentialNotFoundException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.PasswordEncoder;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserLoggerApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserLoggerService implements UserLoggerApi {

    private final UserPersistenceSpi spi;

    @Override
    public User login(String email, String password) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        User user = spi.findUserByEmailAndPassword(
                        email,
                        passwordEncoder.hashPassword(password))
                .orElseThrow(UserCredentialNotFoundException::new);

        UserCheckerService userCheckerService = new UserCheckerService(spi);
        userCheckerService.accountIsActivated(user);

        return user;
    }

}
