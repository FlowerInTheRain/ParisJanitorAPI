package fr.jypast.parisjanitorapi.client.dto.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;

import java.time.LocalDate;
import java.util.UUID;

public record BookingRequest(
        @JsonProperty("propertyId") UUID propertyId,
        @JsonProperty("startDate") LocalDate startDate,
        @JsonProperty("endDate") LocalDate endDate,
        @JsonProperty("tenantId") UUID tenantId,
        @JsonProperty("status") BookingStatus status

) { }