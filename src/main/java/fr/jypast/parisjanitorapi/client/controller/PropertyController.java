package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
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
    private final TokenControllerService tokenControllerService;

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
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        return PropertyDtoMapper.toDto(
                propertyCreatorApi.createProperty(
                        token,
                        PropertyDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PropertyDto> getPropertyById(@RequestHeader HttpHeaders headers, @PathVariable UUID id) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        return propertyFinderApi.findById(id)
                .map(property -> ResponseEntity.ok(PropertyDtoMapper.toDto(property)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
    }

    @PatchMapping("/update/{id}")
    public PropertyDto updateProperty(@RequestHeader HttpHeaders headers, @PathVariable String id, @RequestBody PropertyCreationRequest request) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        return PropertyDtoMapper.toDto(
                propertyUpdaterApi.update(
                        token,
                        PropertyDtoMapper.patchRequestToDomain(request).withId(UuidValidator.validate(id))
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@RequestHeader HttpHeaders headers, @PathVariable UUID id) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        propertyDeleterApi.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/between-dates-with-min-size")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenDatesWithMinSize(
            @RequestHeader HttpHeaders headers,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("minSize") double minSize) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
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
            @RequestHeader HttpHeaders headers,
            @RequestParam("maxSize") double maxSize) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByDateAndMaxSize(availablePropertyIds, maxSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/between-sizes")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenSizes(
            @RequestHeader HttpHeaders headers,
            @RequestParam("minSize") double minSize,
            @RequestParam("maxSize") double maxSize) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findBySizeRange(minSize, maxSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountry(
            @RequestHeader HttpHeaders headers,
            @RequestParam("country") String country) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
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
            @RequestHeader HttpHeaders headers,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByRoomsAndCapacity(availablePropertyIds, rooms, capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithRoomsAndCapacity(
            @RequestHeader HttpHeaders headers,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByRoomsAndCapacity(null, rooms, capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-rooms")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithRooms(
            @RequestHeader HttpHeaders headers,
            @RequestParam("rooms") int rooms) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByRooms(rooms)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithCapacity(
            @RequestHeader HttpHeaders headers,
            @RequestParam("capacity") int capacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByCapacity(capacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-rooms")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinRooms(
            @RequestHeader HttpHeaders headers,
            @RequestParam("minRooms") int minRooms) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByMinRooms(minRooms)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinCapacity(
            @RequestHeader HttpHeaders headers,
            @RequestParam("minCapacity") int minCapacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByMinCapacity(minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/with-min-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesWithMinRoomsAndCapacity(
            @RequestHeader HttpHeaders headers,
            @RequestParam("minRooms") int minRooms,
            @RequestParam("minCapacity") int minCapacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableProperties = propertyFinderApi.findByMinRoomsAndCapacity(minRooms, minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDates(
            @RequestHeader HttpHeaders headers,
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndIds(country, availablePropertyIds)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates-with-rooms-and-capacity")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsAndCapacity(
            @RequestHeader HttpHeaders headers,
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("minRooms") int minRooms,
            @RequestParam("minCapacity") int minCapacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndMinRoomsAndCapacity(country, availablePropertyIds, minRooms, minCapacity)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates-with-rooms-capacity-apartment")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsCapacityApartment(
            @RequestHeader HttpHeaders headers,
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndTypeAndRoomsAndCapacity(country, availablePropertyIds, rooms, capacity, PropertyType.APARTMENT)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }

    @GetMapping("/available/by-country-between-dates-with-rooms-capacity-house")
    public ResponseEntity<List<PropertyDto>> getPropertiesByCountryBetweenDatesWithRoomsCapacityHouse(
            @RequestHeader HttpHeaders headers,
            @RequestParam("country") String country,
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("rooms") int rooms,
            @RequestParam("capacity") int capacity) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByCountryAndTypeAndRoomsAndCapacity(country, availablePropertyIds, rooms, capacity, PropertyType.HOUSE)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableProperties);
    }


    @GetMapping("/available/apartments")
    public ResponseEntity<List<PropertyDto>> getAvailableApartments(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableApartments = propertyFinderApi.findAvailableByType(PropertyType.APARTMENT)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableApartments);
    }

    @GetMapping("/available/houses")
    public ResponseEntity<List<PropertyDto>> getAvailableHouses(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        tokenControllerService.getUserByToken(token);
        List<PropertyDto> availableHouses = propertyFinderApi.findAvailableByType(PropertyType.HOUSE)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(availableHouses);
    }

    @GetMapping("/awaited")
    public ResponseEntity<List<PropertyDto>> getAllAwaitedProperties() {
        List<PropertyDto> awaitedProperties = propertyFinderApi.findAllAwaitedProperties()
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(awaitedProperties);
    }

    @GetMapping("/validated")
    public ResponseEntity<List<PropertyDto>> getAllValidatedProperties() {
        List<PropertyDto> validatedProperties = propertyFinderApi.findAllValidatedProperties()
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(validatedProperties);
    }
}
