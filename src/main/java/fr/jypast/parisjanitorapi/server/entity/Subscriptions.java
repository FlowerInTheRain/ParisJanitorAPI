package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "subscriptions")
public class Subscriptions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "is_active",  nullable = false)
	private Boolean isActive;
	
	@Column(name = "plan_type",  nullable = false)
	private String planType;
	
	@Column(name = "payment_failure",  nullable = false)
	private Boolean paymentFailure;
	
	@Column(name = "automatic_renewal",  nullable = false)
	private Boolean automaticRenewal;
	
	@Column(name = "subscription_date",  nullable = false)
	private Date subscriptionDate;
	
	@OneToOne(mappedBy = "userSubscription")
	private UserEntity user;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "card_expiration_date")
	private String cardExpirationDate;
	
	@Column(name = "card_cryptogram")
	private String cardCryptogram;
	
}
