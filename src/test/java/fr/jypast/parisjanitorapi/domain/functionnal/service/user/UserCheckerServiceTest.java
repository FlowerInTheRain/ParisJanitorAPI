package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.barlords.parisjanitorapi.domain.functionnal.exception.user.EmailAlreadyUsedException;
import fr.barlords.parisjanitorapi.domain.functionnal.exception.user.PseudoAlreadyUsedException;
import fr.barlords.parisjanitorapi.domain.functionnal.exception.user.AccountNotValidatedException;
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

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCheckerServiceTest {

    @InjectMocks
    private UserCheckerService service;

    @Mock
    private UserPersistenceSpi spi;

    @Test
    void should_throw_error_because_pseudo_is_not_available() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findByPseudo(given.getPseudo())).thenReturn(Optional.of(given));

        Assertions.assertThrows(PseudoAlreadyUsedException.class, () -> service.pseudoIsAvailable(given));
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_pseudo_is_available() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findByPseudo(given.getPseudo())).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(() -> service.pseudoIsAvailable(given));
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_throw_error_because_email_is_not_available() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.of(given));

        Assertions.assertThrows(EmailAlreadyUsedException.class, () -> service.emailIsAvailable(given));
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_email_is_available() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.empty());
        verifyNoMoreInteractions(spi);

        Assertions.assertDoesNotThrow(() -> service.emailIsAvailable(given));
    }

    @Test
    void should_throw_error_because_account_is_not_activated() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        Assertions.assertThrows(AccountNotValidatedException.class, () -> service.accountIsActivated(given));
    }

    @Test
    void should_user_is_activated() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .activated(true)
                .build();

        Assertions.assertDoesNotThrow(() -> service.accountIsActivated(given));
    }
}
