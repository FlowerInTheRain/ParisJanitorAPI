package fr.jypast.parisjanitorapi.client.service;

import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.TokenNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthVerifierService {

    private final TokenControllerService tokenControllerService;

    public UUID getToken(HttpHeaders headers) {
        String bearerToken = Objects.requireNonNull(headers.getFirst("Authorization"));
        String tokenString = bearerToken.split(" ")[1];

        UUID token = UuidValidator.validate(tokenString);

        if (!tokenControllerService.tokenExists(token)) {
            throw new TokenNotValidException();
        }

        return token;
    }
}
