package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;

public interface BookingDtoMapper {

    static BookingDto toDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getPropertyId(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTenantId(),
                booking.getStatus(),
                booking.getNumberOfAdults(),
                booking.getNumberOfChildren(),
                booking.getNumberOfBabies(),
                booking.getNumberOfPets()
        );
    }

    static Booking toDomain(BookingRequest request) {
        return Booking.builder()
                .propertyId(request.propertyId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(request.status())
                .numberOfAdults(request.numberOfAdults())
                .numberOfChildren(request.numberOfChildren())
                .numberOfBabies(request.numberOfBabies())
                .numberOfPets(request.numberOfPets())
                .build();
    }

    static Booking creationRequestToDomain(BookingRequest request) {
        return toDomain(request);
    }
}
