package fr.jypast.parisjanitorapi.domain.port.in.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

public interface UserLoggerApi {

    User login(String email, String password);

}
