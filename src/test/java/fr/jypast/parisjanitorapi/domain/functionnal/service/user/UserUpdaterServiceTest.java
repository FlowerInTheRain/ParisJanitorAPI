package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUpdaterServiceTest {

    @InjectMocks
    private UserUpdaterService service;

    @Mock
    private UserPersistenceSpi spi;

    @Mock
    private TokenControllerService tokenControllerService;

    @Mock
    private UserCheckerService userCheckerService;
/*
    @Test
    void should_not_update_user_because_token_is_invalid() {
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

        when(tokenControllerService.getUserByToken(given.getToken().toString())).thenThrow();
        when(spi.findByToken(given.getToken())).thenReturn(Optional.empty());

        Assertions.assertThrows(TokenNotValidException.class, () -> service.update(given.getToken().toString(), given));
    }

    @Test
    void should_not_update_user_email_is_unavailable() {
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
        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.of(given));

        Assertions.assertThrows(EmailAlreadyUsedException.class, () -> service.update(given.getToken().toString(), given));
    }

    @Test
    void should_not_update_user_pseudo_is_unavailable() {
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
        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.empty());
        when(spi.findByPseudo(given.getPseudo())).thenReturn(Optional.of(given));

        Assertions.assertThrows(PseudoAlreadyUsedException.class, () -> service.update(given.getToken().toString(), given));
    }
*/
    @Test
    void should_update_user() {
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

        val modified = User.builder()
                .pseudo(pseudo+"1")
                .email(email+"1")
                .password(password+"1")
                .token(token)
                .build();

        when(tokenControllerService.getUserByToken(modified.getToken())).thenReturn(given);
        doNothing().when(userCheckerService).emailIsAvailable(modified);
        doNothing().when(userCheckerService).pseudoIsAvailable(modified);
        when(spi.save(any(User.class))).thenAnswer(i -> Either.right(i.getArguments()[0]));

        val actual = service.update(modified.getToken(), modified);

        Assertions.assertNotEquals(actual.getPseudo(), given.getPseudo());
        Assertions.assertNotEquals(actual.getEmail(), given.getEmail());
        Assertions.assertNotEquals(actual.getPassword(), given.getPassword());
    }
}
