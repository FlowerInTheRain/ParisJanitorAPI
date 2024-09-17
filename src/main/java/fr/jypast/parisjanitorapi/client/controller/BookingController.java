import fr.jypast.parisjanitorapi.client.dto.booking.BookingDto;
import fr.jypast.parisjanitorapi.client.dto.booking.BookingRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.BookingDtoMapper;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingCreatorApi bookingCreatorApi;
    private final BookingFinderApi bookingFinderApi;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request) {
        BookingDto bookingDto = BookingDtoMapper.toDto(
                bookingCreatorApi.createBooking(BookingDtoMapper.toDomain(request))
        );
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<PropertyDto>> getAvailableProperties(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        List<PropertyDto> availableProperties = bookingFinderApi.findAvailablePropertiesBetweenDates(startDate, endDate)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }
}
