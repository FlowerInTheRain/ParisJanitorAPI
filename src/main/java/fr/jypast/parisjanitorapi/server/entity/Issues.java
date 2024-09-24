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
@Table(name = "providers")
public class Issues {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "origin_type")
	private String issueOrigin;
	
	@Column(name = "origin_reference")
	private String issueOriginReference;
	@Column(name = "posted_by_type")
	private String postedByType;
	
	@Column(name = "posted_by_reference")
	private String postedByReference;
	
	
	@Column(name="decision")
	private String decision;
}
