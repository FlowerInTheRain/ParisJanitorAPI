package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "performers_calendar")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformerCalendarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "working_day", nullable = false)
	private String workingDay;
	
	@Column(name = "start_time", nullable = false)
	private Integer startTime;
	
	@Column(name = "end_time", nullable = false)
	private Integer endTime;
	
	@ManyToOne
	@JoinColumn(name="provider_id", nullable=false)
	private ProvidersEntity provider;
}
