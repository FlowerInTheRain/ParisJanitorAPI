package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;
import fr.jypast.parisjanitorapi.server.entity.CalendarEntity;

public interface CalendarEntityMapper {

    static OccupancyCalendar toDomain(CalendarEntity entity) {
        return OccupancyCalendar.builder()
                .id(entity.getId())
                .propertyId(entity.getPropertyId())
                .date(entity.getDate())
                .isAvailable(entity.isAvailable())
                .build();
    }

    static CalendarEntity fromDomain(OccupancyCalendar domain) {
        return CalendarEntity.builder()
                .id(domain.getId())
                .propertyId(domain.getPropertyId())
                .date(domain.getDate())
                .isAvailable(domain.isAvailable())
                .build();
    }
}
