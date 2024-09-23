package fr.jypast.parisjanitorapi.client.dto.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;

import java.sql.Date;
import java.util.UUID;

public record BookingRequest(
        @JsonProperty("propertyId") UUID propertyId,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("endDate") Date endDate,
        @JsonProperty("tenantId") UUID tenantId,
        @JsonProperty("status") BookingStatus status

) { }