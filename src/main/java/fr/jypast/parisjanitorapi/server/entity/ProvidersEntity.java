package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "providers")
public class ProvidersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "name",  nullable = false)
	private String fullName;
	
	@Column(name = "mail",  nullable = false, length = 200, unique = true)
	private String email;
	
	@Column(name = "phone",  nullable = false, length = 10, unique = true)
	private String phoneNumber;
	
	@Column(name = "join_date",  nullable = false)
	private String joinDate;
	
	@Column(name = "join_request_status",  nullable = false)
	private Boolean joinRequestStatus;
	
	@Column(name = "join_request_message",  nullable = false)
	private String joinRequestMessage;
	
	@OneToMany(mappedBy="provider")
	private List<ReferencedPrestationsEntity> referencedPrestation;
}
