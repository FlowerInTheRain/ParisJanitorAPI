package fr.jypast.parisjanitorapi.client.service;

import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
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

    public UUID getToken(HttpHeaders headers) {
        return UuidValidator.validate(
                Objects.requireNonNull(headers.getFirst("Authorization")).split(" ")[1]);
    }
}
