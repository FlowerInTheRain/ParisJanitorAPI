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
                booking.getStatus()
        );
    }

    static Booking toDomain(BookingRequest request) {
        return Booking.builder()
                .propertyId(request.propertyId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .tenantId(request.tenantId())
                .status(request.status())
                .build();
    }

    static Booking creationRequestToDomain(BookingRequest request) {
        return toDomain(request);
    }
}
