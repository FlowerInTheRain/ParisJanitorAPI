package fr.jypast.parisjanitorapi.client.controller;


import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.client.mapper.BookingDtoMapper;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingCreatorApi bookingCreatorApi;
    private final BookingFinderApi bookingFinderApi;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request) {
        BookingDto bookingDto = BookingDtoMapper.toDto(
                bookingCreatorApi.createBooking(BookingDtoMapper.toDomain(request))
        );
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    @ResponseStatus(OK)
    public ResponseEntity<List<BookingDto>> getAvailableProperties(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        List<BookingDto> availableProperties = bookingFinderApi.findAvailablePropertiesBetweenDates(startDate, endDate)
                .stream()
                .map(BookingDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }
}