package fr.jypast.parisjanitorapi.domain.functionnal.service.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class UserModifierService {

    public User updateToken(User user) {
        return user.withToken(UUID.randomUUID())
                .withTokenDate(LocalDate.now());
    }

    public User encodePassword(User user) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        return user.withPassword(passwordEncoder.hashPassword(user.getPassword()));
    }
}
