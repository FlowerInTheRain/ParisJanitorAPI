package fr.jypast.parisjanitorapi.domain.functionnal.service;

import fr.barlords.parisjanitorapi.domain.functionnal.exception.user.TokenNotValidException;
import fr.barlords.parisjanitorapi.domain.functionnal.model.user.User;
import fr.barlords.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenControllerServiceTest {

    @InjectMocks
    private TokenControllerService service;

    @Mock
    private UserPersistenceSpi spi;


    @Test
    void should_not_get_user_because_token_is_invalid() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val token = UUID.randomUUID();
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(token)
                .build();

        when(spi.findByToken(given.getToken())).thenReturn(Optional.empty());

        Assertions.assertThrows(TokenNotValidException.class, () -> service.getUserByToken(given.getToken()));
    }

    @Test
    void should_get_user() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val token = UUID.randomUUID();
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(token)
                .build();

        when(spi.findByToken(given.getToken())).thenReturn(Optional.of(given));
        verifyNoMoreInteractions(spi);

        val actual = service.getUserByToken(given.getToken());

        Assertions.assertEquals(given, actual);
    }
}
