package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.PropertyDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    // Fichier : PropertyController.java
    @GetMapping("/available/between-dates-with-min-size")
    public ResponseEntity<List<PropertyDto>> getAvailablePropertiesBetweenDatesWithMinSize(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
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
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("maxSize") double maxSize) {

        List<UUID> availablePropertyIds = calendarBlockerApi.findAvailablePropertiesBetweenDates(startDate, endDate);
        List<PropertyDto> availableProperties = propertyFinderApi.findByDateAndMaxSize(availablePropertyIds, maxSize)
                .stream()
                .map(PropertyDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(availableProperties);
    }

}
