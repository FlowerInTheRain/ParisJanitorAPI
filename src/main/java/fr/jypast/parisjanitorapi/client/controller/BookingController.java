package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.BookingDtoMapper;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final AuthVerifierService authVerifierService;
    private final BookingCreatorApi bookingCreatorApi;
    private final BookingFinderApi bookingFinderApi;
    private final TokenControllerService tokenControllerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public BookingDto createdBooking(@RequestHeader HttpHeaders headers, @RequestBody BookingRequest request) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        return BookingDtoMapper.toDto(
                bookingCreatorApi.createBooking(
                        token,
                        BookingDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<PropertyDto>> getAvailableProperties(
            @RequestHeader HttpHeaders headers,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate) {
        String userToken = authVerifierService.getToken(headers).toString();

        List<PropertyDto> availableProperties = bookingFinderApi.findAvailablePropertiesBetweenDates(startDate, endDate)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/past")
    public List<BookingDto> getPastBookings(@RequestHeader HttpHeaders headers) {
        UUID tenantId = authVerifierService.getToken(headers);
        Date now = new Date();
        return bookingFinderApi.findBookingsByTenantIdAndEndDateBefore(tenantId, now).stream()
                .map(BookingDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/property/history")
    public List<BookingDto> getPropertyHistory(@RequestHeader HttpHeaders headers, UUID propertyId) {
        Date now = new Date();
        return bookingFinderApi.findBookingsByPropertyIdAndEndDateBefore(propertyId, now).stream()
                .map(BookingDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/current")
    public List<BookingDto> getCurrentBookings(@RequestHeader HttpHeaders headers) {
        UUID tenantId = authVerifierService.getToken(headers);
        Date now = new Date();
        return bookingFinderApi.findBookingsByTenantIdAndDatesBetween(tenantId, now, now).stream()
                .map(BookingDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/upcoming")
    public List<BookingDto> getUpcomingBookings(@RequestHeader HttpHeaders headers) {
        UUID tenantId = authVerifierService.getToken(headers);
        Date now = new Date();
        return bookingFinderApi.findBookingsByTenantIdAndStartDateAfter(tenantId, now).stream()
                .map(BookingDtoMapper::toDto)
                .toList();
    }
}
