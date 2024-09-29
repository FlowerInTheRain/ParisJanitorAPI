package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.server.entity.BookingEntity;

public interface BookingEntityMapper {
    static Booking toDomain(BookingEntity entity) {
        return Booking.builder()
                .id(entity.getId())
                .propertyId(entity.getPropertyId())
                .tenantId(entity.getTenantId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus())
                .numberOfAdults(entity.getNumberOfAdults())
                .numberOfChildren(entity.getNumberOfChildren())
                .numberOfBabies(entity.getNumberOfBabies())
                .numberOfPets(entity.getNumberOfPets())
                .build();
    }

    static BookingEntity fromDomain(Booking domain) {
        return BookingEntity.builder()
                .id(domain.getId())
                .propertyId(domain.getPropertyId())
                .tenantId(domain.getTenantId())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .status(domain.getStatus())
                .numberOfAdults(domain.getNumberOfAdults())
                .numberOfChildren(domain.getNumberOfChildren())
                .numberOfBabies(domain.getNumberOfBabies())
                .numberOfPets(domain.getNumberOfPets())
                .build();
    }
}
