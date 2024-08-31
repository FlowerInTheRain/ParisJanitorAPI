package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

public interface EmailingSpi {

    void sendAccountValidationEmail(User user);

    void sendInvitationCodeEmail(User sender, User receiver, String code);
    void sendCodeVerifier(User user, String code);

}
