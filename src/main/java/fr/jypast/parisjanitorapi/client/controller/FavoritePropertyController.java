package fr.jypast.parisjanitorapi.client.controller;


import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/favorites")
public class FavoritePropertyController {

    private final AuthVerifierService authVerifierService;
    private final PropertyFinderApi propertyFinderApi;
    private final PropertyCreatorApi propertyCreatorApi;
    private final PropertyDeleterApi propertyDeleterApi;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavorite(
            @RequestParam UUID userId, @RequestParam UUID propertyId) {
        FavoriteProperty favorite = propertyCreatorApi.addFavorite(userId, propertyId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteProperty>> getUserFavorites(@PathVariable UUID userId) {
        List<FavoriteProperty> favorites = propertyFinderApi.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(@RequestParam UUID userId, @RequestParam UUID propertyId) {
        propertyDeleterApi.removeFavorite(userId, propertyId);
    }
}