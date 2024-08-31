package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.client.mapper.PropertyMapper;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyCreatorApi propertyCreatorApi;
}
