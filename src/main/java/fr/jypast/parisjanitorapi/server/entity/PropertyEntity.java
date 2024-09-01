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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "property")
public class PropertyEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "address", unique = true, nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    @Column(name = "ownerId", nullable = false)
    private UUID ownerId;

    @Column(name = "isValidated")
    private boolean isValidated;
}

