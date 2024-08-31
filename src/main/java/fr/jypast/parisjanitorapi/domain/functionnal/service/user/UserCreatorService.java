package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.EmailingSpi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {

    private final UserPersistenceSpi spi;
    private final UserCheckerService userCheckerService;
    private final EmailingSpi emailSenderSpi;

    @Override
    public User create(User user) {
        userCheckerService.emailIsAvailable(user);

        UserModifierService userModifierService = new UserModifierService();
        user = userModifierService.updateToken(user);
        user = userModifierService.encodePassword(user);

        User savedUser = spi.save(user)
                .getOrElseThrow(DataNotSaveException::new);

        emailSenderSpi.sendAccountValidationEmail(savedUser);

        return savedUser;
    }

    @Override
    public User createWithId(User user, UUID id) {
        Optional<User> existingUserOpt = spi.findByToken(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            return updateExistingUser(existingUser, user);
        } else {
            return this.create(user.withId(id));
        }
    }

    private User updateExistingUser(User existingUser, User newUserData) {
        existingUser = existingUser.withEmail(newUserData.getEmail())
                .withPassword(newUserData.getPassword())
                .withLastName(newUserData.getLastName())
                .withFirstName(newUserData.getFirstName())
                .withBirthday(newUserData.getBirthday())
                .withPhoneNumber(newUserData.getPhoneNumber())
                .withRegion(newUserData.getRegion())
                .withAdresse1(newUserData.getAdresse1())
                .withAdresse2(newUserData.getAdresse2());

        return spi.save(existingUser)
                .getOrElseThrow(DataNotSaveException::new);
    }
}
