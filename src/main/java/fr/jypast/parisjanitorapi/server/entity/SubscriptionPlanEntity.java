package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "subscription_plan")
public class SubscriptionPlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "ads",  nullable = false)
	private Boolean ads;
	
	@Column(name = "subscription_name",  nullable = false)
	private String name;
	
	@Column(name = "monthly_price",nullable = false)
	private Float monthlyPrice;
	
	@Column(name = "annual_price",nullable = false)
	private Float annual_price;
	
	@Column(name = "comment_publish", nullable = false)
	private Boolean commentAndPublish;
	
	@Column(name = "service_sale", nullable = false)
	private Boolean serviceSale;
	
	@Column(name = "service_sale_value", nullable = false)
	private Integer saleValue;
	
	@Column(name = "free_service", nullable = false)
	private Boolean freeService;
	
	@Column(name = "free_service_occurence", unique = true, nullable = false)
	private Integer freeServiceOccurence;
	
	@Column(name = "free_service_amount")
	private Integer freeServiceAmount;
	
	@Column(name = "is_vip", unique = true, nullable = false)
	private Boolean isVip;
	
	@Column(name = "subscription_sale",nullable = false)
	private Integer subscriptionSale;
	
	@Column(name = "subscription_sale_amount",nullable = false)
	private Integer subscriptionSaleAmount;
	
	@OneToOne(mappedBy = "userSubscriptionPlan")
	private UserEntity user;
}
