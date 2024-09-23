package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.sql.Date;
import java.util.List;

public interface BookingFinderApi {
    List<Property> findAvailablePropertiesBetweenDates(Date startDate, Date endDate);
}
