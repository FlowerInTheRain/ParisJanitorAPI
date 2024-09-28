package fr.jypast.parisjanitorapi.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.AccommodationType;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.ConciergerieType;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.ContactSlot;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "property")
public class PropertyEntity implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "adress", unique = true, nullable = false)
    private String adress;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    @Column(name = "ownerId", nullable = false)
    private UUID ownerId;

    @Column(name = "isValidated")
    private boolean isValidated;

    @Column(name = "numberOfRooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "propertyType", nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "size", nullable = false)
    private double size;

    @ElementCollection
    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @ElementCollection(targetClass = ContactSlot.class)
    @CollectionTable(name = "contact_slots", joinColumns = @JoinColumn(name = "property_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "contactSlot")
    private List<ContactSlot> contactSlots;

    @Column(name = "privacyDeclaration", nullable = false)
    private boolean privacyDeclaration;

    @Column(name = "conciergerieType", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConciergerieType conciergerieType;

    @Column(name = "accommodationType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Column(name = "pricePerNight", nullable = false)
    private double pricePerNight;

    @Column(name = "city", nullable = false)
    private String city;
}
