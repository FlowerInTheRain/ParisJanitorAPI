package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.BookingDtoMapper;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final AuthVerifierService authVerifierService;
    private final BookingCreatorApi bookingCreatorApi;
    private final BookingFinderApi bookingFinderApi;

    @PostMapping
    @ResponseStatus(CREATED)
    public BookingDto createBooking(
            @RequestHeader HttpHeaders headers, @RequestBody BookingRequest request) {
        return BookingDtoMapper.toDto(
                bookingCreatorApi.createBooking(
                        authVerifierService.getToken(headers),
                        BookingDtoMapper.creationRequestToDomain(request)
                )
        );
    }


    @GetMapping("/available")
    public ResponseEntity<List<PropertyDto>> getAvailableProperties(
            @RequestHeader HttpHeaders headers,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        String userToken = authVerifierService.getToken(headers).toString();

        List<PropertyDto> availableProperties = bookingFinderApi.findAvailablePropertiesBetweenDates(startDate, endDate)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }
}
