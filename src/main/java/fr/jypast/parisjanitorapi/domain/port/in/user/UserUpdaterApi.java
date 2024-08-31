package fr.jypast.parisjanitorapi.domain.port.in.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.UUID;

public interface UserUpdaterApi {

    User update(UUID token, User user);

    User updatePwd(UUID token, User user);

}
