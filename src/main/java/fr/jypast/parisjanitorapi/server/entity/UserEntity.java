package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
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

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "region")
    private String region;

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "profilePicture")
    private String profilePicture;

    @Column(name = "token", nullable = false)
    private UUID token;

    @Column(name = "token_date", nullable = false)
    private Date tokenDate;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name = "verification_code", nullable = true)
    private String verificationCode;

    @Column(name = "password_verification", nullable = true)
    private String passwordVerification;
    
    
    @Column(name = "free_prestation_available", nullable = true)
    private Boolean freePrestationUsed;
    
    @Column(name = "free_prestation_date", nullable = true)
    private Date freePrestationDate;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_plan_id", referencedColumnName = "id")
    private SubscriptionPlanEntity userSubscriptionPlan;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscriptions userSubscription;
}
