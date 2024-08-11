package fr.jypast.parisjanitorapi.domain.functionnal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PasswordEncoderServiceTest {

    @InjectMocks
    private PasswordEncoder service;

    @Test
    void should_encode_password() {
        String given = "mypassword";
        String actual = service.hashPassword(given);
        Assertions.assertNotEquals(actual, given);
    }
}
