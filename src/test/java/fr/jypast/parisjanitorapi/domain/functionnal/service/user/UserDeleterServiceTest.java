package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.barlords.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDeleterServiceTest {

    @InjectMocks
    private UserDeleterService service;

    @Mock
    private UserPersistenceSpi spi;

    @Test
    void should_delete_user_by_id() {
        UUID id = UUID.randomUUID();

        doNothing().when(spi).deleteById(id);

        Assertions.assertDoesNotThrow(() -> service.deleteById(id));
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_delete_user_by_token() {
        UUID token = UUID.randomUUID();

        doNothing().when(spi).deleteByToken(token);

        Assertions.assertDoesNotThrow(() -> service.deleteByToken(token));
        verifyNoMoreInteractions(spi);
    }

}
