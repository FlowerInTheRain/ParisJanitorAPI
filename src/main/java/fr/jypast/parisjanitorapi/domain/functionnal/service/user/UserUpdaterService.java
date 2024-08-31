package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.user.UserUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserUpdaterService implements UserUpdaterApi {

    private final UserPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    private final UserCheckerService userCheckerService;

    @Override
    public User update(UUID token, User user) {
        User previousUser = tokenControllerService.getUserByToken(token);

        userCheckerService.emailIsAvailable(user);

        User patchedUser = previousUser.withEmail(user.getEmail())
                .withLastName(user.getLastName())
                .withFirstName(user.getFirstName())
                .withBirthday(user.getBirthday())
                .withPhoneNumber(user.getPhoneNumber())
                .withRegion(user.getRegion())
                .withAdresse1(user.getAdresse1())
                .withAdresse2(user.getAdresse2());

        return spi.save(patchedUser)
                .getOrElseThrow(DataNotSaveException::new);
    }

    @Override
    public User updatePwd(UUID token, User user) {
        User previousUser = tokenControllerService.getUserByToken(token);

        userCheckerService.emailIsAvailable(user);

        User patchedUser = previousUser.withEmail(user.getEmail())
                .withPassword(user.getPassword());

        return spi.save(patchedUser)
                .getOrElseThrow(DataNotSaveException::new);
    }

}
