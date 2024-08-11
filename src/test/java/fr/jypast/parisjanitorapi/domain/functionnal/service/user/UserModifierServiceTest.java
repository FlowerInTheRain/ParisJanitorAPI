package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.barlords.parisjanitorapi.domain.functionnal.model.user.User;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserModifierServiceTest {

    @InjectMocks
    private UserModifierService service;

    @Test
    void should_update_user_token() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        val actual = service.updateToken(given);

        Assertions.assertNotEquals(actual.getToken(), given.getToken());
        Assertions.assertNotEquals(actual.getTokenDate(), given.getTokenDate());
    }

    @Test
    void should_encode_user_password() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        val actual = service.encodePassword(given);

        Assertions.assertNotEquals(actual.getPassword(), given.getPassword());
    }

}
