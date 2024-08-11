package fr.jypast.parisjanitorapi.bootstrap;

import fr.jypast.parisjanitorapi.bootstrap.config.ParisJanitorApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import({ParisJanitorApiConfiguration.class})
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {"fr.jypast"})
public class ParisJanitorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParisJanitorApiApplication.class, args);
    }

}
