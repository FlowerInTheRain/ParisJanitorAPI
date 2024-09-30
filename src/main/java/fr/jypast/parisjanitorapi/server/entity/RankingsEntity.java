package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers_rankings")
public class RankingsEntity {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "origin_id", nullable = false, updatable = false)
	private UUID originId;
	
	@Column(name = "origin_type", nullable = false, updatable = false)
	private String originType;
	
	
	
	@Column(name = "target_id", nullable = false, updatable = false)
	private UUID targetId;
	
	@Column(name = "target_type", nullable = false, updatable = false)
	private String targetType;
	
	@Column(name = "ranking", nullable = false)
	private Integer ranking;
	
	@Column(name = "comment")
	private String comment;
}
