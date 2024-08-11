package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.barlords.parisjanitorapi.domain.functionnal.model.user.User;
import fr.barlords.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFinderServiceTest {

    @InjectMocks
    private UserFinderService service;

    @Mock
    private UserPersistenceSpi spi;

    @Test
    void should_find_all_user() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val user1 = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();
        val given = List.of(user1);

        when(spi.findAll()).thenReturn(given);
        verifyNoMoreInteractions(spi);

        val actual = service.findAll();

        Assertions.assertEquals(given, actual);
    }

    @Test
    void should_find_user_by_id() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findById(given.getId())).thenReturn(Optional.of(given));
        verifyNoMoreInteractions(spi);

        val actual = service.findById(given.getId());

        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    void should_not_find_user_by_id() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .build();

        when(spi.findById(given.getId())).thenReturn(Optional.empty());
        verifyNoMoreInteractions(spi);

        val actual = service.findById(given.getId());

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void should_find_user_by_email() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(UUID.randomUUID())
                .build();

        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.of(given));
        verifyNoMoreInteractions(spi);

        val actual = service.findByEmail(given.getEmail());

        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    void should_not_find_user_by_email() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(UUID.randomUUID())
                .build();

        when(spi.findByEmail(given.getEmail())).thenReturn(Optional.empty());
        verifyNoMoreInteractions(spi);

        val actual = service.findByEmail(given.getEmail());

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void should_find_user_by_pseudo() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(UUID.randomUUID())
                .build();

        when(spi.findByPseudo(given.getPseudo())).thenReturn(Optional.of(given));
        verifyNoMoreInteractions(spi);

        val actual = service.findByPseudo(given.getPseudo());

        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    void should_not_find_user_by_pseudo() {
        val pseudo = "usertest";
        val email = "test@test.fr";
        val password = "userpassword";
        val given = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(password)
                .token(UUID.randomUUID())
                .build();

        when(spi.findByPseudo(given.getPseudo())).thenReturn(Optional.empty());
        verifyNoMoreInteractions(spi);

        val actual = service.findByPseudo(given.getPseudo());

        Assertions.assertTrue(actual.isEmpty());
    }

}
