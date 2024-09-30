package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "providers")
public class Performance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provider_id", referencedColumnName = "id")
	private PropertyEntity provider;
	
	@Column(name = "name",  nullable = false)
	private Date date;
	
	@Column(name = "status",  nullable = false)
	private String status;
}
