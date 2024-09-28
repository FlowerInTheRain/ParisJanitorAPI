package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/properties")
public class PropertyController {

    private final AuthVerifierService authVerifierService;
    private final PropertyFinderApi propertyFinderApi;
    private final PropertyCreatorApi propertyCreatorApi;
    
    private final PropertyUpdaterApi propertyUpdaterApi;
    private final PropertyDeleterApi propertyDeleterApi;
    private final CalendarBlockerApi calendarBlockerApi;

    @GetMapping
    @ResponseStatus(OK)
    public List<PropertyDto> getProperties() {
        return propertyFinderApi.findAll()
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PropertyDto createProperty(@RequestHeader HttpHeaders headers, @RequestBody PropertyCreationRequest request) {
        return PropertyDtoMapper.toDto(
                propertyCreatorApi.createProperty(
                        authVerifierService.getToken(headers),
                        PropertyDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @PatchMapping("/update/{id}")
    public PropertyDto updateProperty(
            @RequestHeader HttpHeaders headers,
            @PathVariable String id,
            @RequestBody PropertyCreationRequest request) {
        return PropertyDtoMapper.toDto(
                propertyUpdaterApi.update(
                        authVerifierService.getToken(headers),
                        PropertyDtoMapper.patchRequestToDomain(request).withId(UuidValidator.validate(id))
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable UUID id) {
        propertyDeleterApi.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/between-dates-with-min-size")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenDatesWithMinSize(
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("minSize") double minSize) {
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByDateAndMinSize(availablePropertyIds, minSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/between-dates-with-max-size")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenDatesWithMaxSize(
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("maxSize") double maxSize) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByDateAndMaxSize(availablePropertyIds, maxSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/between-sizes")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenSizes(
            @RequestParam("minSize") double minSize,
            @RequestParam("maxSize") double maxSize) {

        List<PropertyDto> availableProperties = propertyFinderApi.findBySizeRange(minSize, maxSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountry(
            @RequestParam("country") String country) {

        List<PropertyDto> propertiesByCountry = propertyFinderApi.findByCountry(country)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(propertiesByCountry);
    }

    @GetMapping("/available/between-dates-with-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenDatesWithRoomsAndCapacity(
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);

        List<PropertyDto> availableProperties = propertyFinderApi.findByRoomsAndCapacity(availablePropertyIds, rooms, capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithRoomsAndCapacity(
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByRoomsAndCapacity(null, rooms, capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }
    @GetMapping("/available/with-rooms")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithRooms(
            @RequestParam("rooms") int rooms) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByRooms(rooms)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithCapacity(
            @RequestParam("capacity") int capacity) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByCapacity(capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-rooms")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinRooms(
            @RequestParam("minRooms") int minRooms) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByMinRooms(minRooms)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinCapacity(
            @RequestParam("minCapacity") int minCapacity) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByMinCapacity(minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinRoomsAndCapacity(
            @RequestParam("minRooms") int minRooms,
            @RequestParam("minCapacity") int minCapacity) {

        List<PropertyDto> availableProperties = propertyFinderApi.findByMinRoomsAndCapacity(minRooms, minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDates(
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);

        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndIds(country, availablePropertyIds)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }
    @GetMapping("/available/by-country-between-dates-with-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsAndCapacity(
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("minRooms") int minRooms,
            @RequestParam("minCapacity") int minCapacity) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);

        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndMinRoomsAndCapacity(country, availablePropertyIds, minRooms, minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates-with-rooms-capacity-apartment")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsCapacityApartment(
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);

        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndTypeAndRoomsAndCapacity(country, availablePropertyIds, rooms, capacity, PropertyType.APARTMENT)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates-with-rooms-capacity-house")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsCapacityHouse(
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);

        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndTypeAndRoomsAndCapacity(country, availablePropertyIds, rooms, capacity, PropertyType.HOUSE)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/apartments")
    public ResponseEntity<List<PropertyDto>> getAvailableApartments() {
        List<PropertyDto> availableApartments = propertyFinderApi.findAvailableByType(PropertyType.APARTMENT)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableApartments);
    }

    @GetMapping("/available/houses")
    public ResponseEntity<List<PropertyDto>> getAvailableHouses() {
        List<PropertyDto> availableHouses = propertyFinderApi.findAvailableByType(PropertyType.HOUSE)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableHouses);
    }
}
