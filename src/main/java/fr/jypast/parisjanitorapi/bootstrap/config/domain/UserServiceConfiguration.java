package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.user.*;
import fr.jypast.parisjanitorapi.domain.port.in.user.*;
import fr.jypast.parisjanitorapi.domain.port.out.EmailingSpi;
import fr.jypast.parisjanitorapi.domain.port.out.UserPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public TokenControllerService tokenControllerService(
            UserPersistenceSpi spi
    ) {
        return new TokenControllerService(spi);
    }

    @Bean
    public UserCheckerService userCheckerService(
            UserPersistenceSpi spi
    ) {
        return new UserCheckerService(spi);
    }

    @Bean
    public UserCreatorApi userCreatorApi(
            UserPersistenceSpi spi,
            UserCheckerService userCheckerService,
            EmailingSpi emailingSpi
    ) {
        return new UserCreatorService(spi, userCheckerService, emailingSpi);
    }

    @Bean
    public UserDeleterApi userDeleterApi(
            UserPersistenceSpi spi
    ) {
        return new UserDeleterService(spi);
    }

    @Bean
    public UserFinderApi userFinderApi(
            UserPersistenceSpi spi
    ) {
        return new UserFinderService(spi);
    }

    @Bean
    public UserUpdaterApi userUpdaterApi(
            UserPersistenceSpi spi,
            TokenControllerService tokenControllerService,
            UserCheckerService userCheckerService
    ) {
        return new UserUpdaterService(spi, tokenControllerService, userCheckerService);
    }

    @Bean
    public UserLoggerApi userLoggerApi(
            UserPersistenceSpi spi
    ) {
        return new UserLoggerService(spi);
    }

    @Bean
    public UserVerifierApi userVerifierApi(
            UserPersistenceSpi spi
    ) {
        return new UserVerifierService(spi);
    }

}
