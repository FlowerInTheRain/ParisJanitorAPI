package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;

public interface BookingDtoMapper {

    static Booking toDomain(BookingRequest request) {
        return Booking.builder()
                .propertyId(request.propertyId())
                .tenantId(request.tenantId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(BookingStatus.RESERVED)
                .build();
    }

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
}
