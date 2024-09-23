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
@Table(name = "referentiel_prestations")
public class ReferencedPrestationsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "service_name",  nullable = false, unique = true)
	private String serviceName;
	
	@Column(name = "price_type", nullable = false)
	private String priceType;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "price_variation")
	private Integer priceVariation;
	
	@Column(name = "price_increment")
	private Integer priceIncrement;
	
	@Column(name = "price_increment_unit")
	private String priceIncrementUnit;
	
	@Column(name = "reported")
	private Boolean reported;
	
	@Column(name = "report_reason")
	private String reportReason;
	
	@Column(name = "habilitation")
	private Boolean habilitation;
	
	@Column(name = "habilitation_name")
	private String habilitations;
}
