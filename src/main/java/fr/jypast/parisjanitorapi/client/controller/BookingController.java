package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.BookingDtoMapper;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingUpdaterApi;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private final BookingUpdaterApi bookingUpdaterApi;
    private final TokenControllerService tokenControllerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public BookingDto createdBooking(@RequestHeader HttpHeaders headers, @RequestBody BookingRequest request) {
        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        if (tenantId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user token: Tenant ID could not be determined.");
        }

        Booking booking = BookingDtoMapper.creationRequestToDomain(request).withTenantId(tenantId.getId());

        return BookingDtoMapper.toDto(
                bookingCreatorApi.createBooking(
                        token,
                        booking
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

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkPropertyAvailability(
            @RequestParam UUID propertyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        boolean isAvailable = bookingFinderApi.findAvailablePropertiesBetweenDates(startDate, endDate)
                .stream()
                .anyMatch(property -> property.getId().equals(propertyId));

        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<BookingDto>> getPendingBookings(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        if (tenantId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user token: Tenant ID could not be determined.");
        }

        List<BookingDto> pendingBookings = bookingFinderApi.findPendingBookingsByTenantId(tenantId.getId())
                .stream()
                .map(BookingDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(pendingBookings);
    }

    @PutMapping("/{bookingId}/accept")
    public ResponseEntity<BookingDto> acceptBooking(
            @RequestHeader HttpHeaders headers,
            @PathVariable UUID bookingId) {

        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        try {
            BookingDto updatedBooking = BookingDtoMapper.toDto(
                    bookingUpdaterApi.updateBookingStatus(bookingId, BookingStatus.RESERVED, tenantId.getId())
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (DataNotSaveException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update booking status.", e);
        }
    }

    @PutMapping("/{bookingId}/start")
    public ResponseEntity<BookingDto> startBooking(
            @RequestHeader HttpHeaders headers,
            @PathVariable UUID bookingId) {

        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        try {
            BookingDto updatedBooking = BookingDtoMapper.toDto(
                    bookingUpdaterApi.updateBookingStatus(bookingId, BookingStatus.EN_COURS, tenantId.getId())
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (DataNotSaveException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update booking status.", e);
        }
    }

    @PutMapping("/{bookingId}/finish")
    public ResponseEntity<BookingDto> finishBooking(
            @RequestHeader HttpHeaders headers,
            @PathVariable UUID bookingId) {

        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        try {
            BookingDto updatedBooking = BookingDtoMapper.toDto(
                    bookingUpdaterApi.updateBookingStatus(bookingId, BookingStatus.FINISHED, tenantId.getId())
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (DataNotSaveException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update booking status.", e);
        }
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingDto> cancelBooking(
            @RequestHeader HttpHeaders headers,
            @PathVariable UUID bookingId) {

        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        try {
            BookingDto updatedBooking = BookingDtoMapper.toDto(
                    bookingUpdaterApi.updateBookingStatus(bookingId, BookingStatus.ANNULED, tenantId.getId())
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (DataNotSaveException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update booking status.", e);
        }
    }

    @PutMapping("/{bookingId}/refuse")
    public ResponseEntity<BookingDto> refuseBooking(
            @RequestHeader HttpHeaders headers,
            @PathVariable UUID bookingId) {

        UUID token = authVerifierService.getToken(headers);
        User tenantId = tokenControllerService.getUserByToken(token);

        try {
            BookingDto updatedBooking = BookingDtoMapper.toDto(
                    bookingUpdaterApi.updateBookingStatus(bookingId, BookingStatus.REFUSED, tenantId.getId())
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (DataNotSaveException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update booking status.", e);
        }
    }

    @GetMapping("/has-booking")
    public ResponseEntity<Boolean> hasBookingBetweenDates(
            @RequestHeader HttpHeaders headers,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        UUID token = authVerifierService.getToken(headers);
        User tenant = tokenControllerService.getUserByToken(token);

        if (tenant == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean hasBooking = bookingFinderApi
                .findBookingsByTenantIdAndDatesOverlap(tenant.getId(), startDate, endDate)
                .stream()
                .findAny()
                .isPresent();

        return new ResponseEntity<>(hasBooking, HttpStatus.OK);

    }

    @GetMapping("/finished")
    public ResponseEntity<List<BookingDto>> getFinishedBookings(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        UUID tenantId = tokenControllerService.getUserByToken(token).getId();

        List<Booking> bookings = bookingFinderApi.findBookingsByTenantIdAndStatus(tenantId, BookingStatus.FINISHED);
        List<BookingDto> bookingDtos = bookings.stream().map(BookingDtoMapper::toDto).toList();

        return ResponseEntity.ok(bookingDtos);
    }

    @GetMapping("/cancelled-or-refused")
    public ResponseEntity<List<BookingDto>> getCancelledOrRefusedBookings(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        UUID tenantId = tokenControllerService.getUserByToken(token).getId();

        List<Booking> bookings = bookingFinderApi.findBookingsByTenantIdAndStatuses(
                tenantId, List.of(BookingStatus.ANNULED, BookingStatus.REFUSED)
        );
        List<BookingDto> bookingDtos = bookings.stream().map(BookingDtoMapper::toDto).toList();

        return ResponseEntity.ok(bookingDtos);
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<BookingDto>> getReservedBookings(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        UUID tenantId = tokenControllerService.getUserByToken(token).getId();

        List<Booking> bookings = bookingFinderApi.findBookingsByTenantIdAndStatus(tenantId, BookingStatus.RESERVED);
        List<BookingDto> bookingDtos = bookings.stream().map(BookingDtoMapper::toDto).toList();

        return ResponseEntity.ok(bookingDtos);
    }


}
