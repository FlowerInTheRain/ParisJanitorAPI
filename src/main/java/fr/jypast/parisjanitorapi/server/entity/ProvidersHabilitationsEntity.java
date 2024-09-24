package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "providers_habilitations")
public class ProvidersHabilitationsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "certificate_name",  nullable = false)
	private String certificateName;
	
	@Column(name = "certificate_url",  nullable = false)
	private String url;
	@Builder.Default
	@Column(name = "approved",  nullable = false)
	private Boolean approved = false;
	@Builder.Default
	
	@Column(name = "submission_date",  nullable = false)
	private Date submissionDate = new Date(Instant.now().getEpochSecond());
	
	@Column(name = "valid_until",  nullable = false)
	private Date validityDate;
	
	@ManyToOne
	@JoinColumn(name="provider_id", nullable=false)
	private ProvidersEntity provider;
}
