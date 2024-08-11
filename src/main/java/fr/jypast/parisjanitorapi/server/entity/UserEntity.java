package fr.jypast.parisjanitorapi.server.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "pseudo", unique = true, nullable = false)
    private String pseudo;

    @Column(name = "token", nullable = false)
    private UUID token;

    @Column(name = "token_date", nullable = false)
    private LocalDate tokenDate;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name = "verification_code")
    private String verificationCode;


}
