package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.EmailingSpi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {

    private final UserPersistenceSpi spi;

    private final UserCheckerService userCheckerService;

    private final EmailingSpi emailSenderSpi;

    @Override
    public User create(User user) {
        userCheckerService.pseudoIsAvailable(user);
        userCheckerService.emailIsAvailable(user);

        UserModifierService userModifierService = new UserModifierService();
        user = userModifierService.updateToken(user);
        user = userModifierService.encodePassword(user);

        User savedUser = spi.save(user)
                .getOrElseThrow(DataNotSaveException::new);

        emailSenderSpi.sendAccountValidationEmail(savedUser);

        return savedUser;
    }

    public User createWithId(User user, UUID id) {
        return this.create(user.withId(id));
    }


}
