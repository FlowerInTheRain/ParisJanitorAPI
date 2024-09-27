package fr.jypast.parisjanitorapi.client.controller;


import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    private final TokenControllerService tokenControllerService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavorite(
            @RequestHeader HttpHeaders headers,
            @RequestParam UUID propertyId) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);
        FavoriteProperty favorite = propertyCreatorApi.addFavorite(user.getId(), propertyId);
    }


    @GetMapping("/user/me")
    public ResponseEntity<List<FavoriteProperty>> getUserFavorites(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);
        List<FavoriteProperty> favorites = propertyFinderApi.getUserFavorites(user.getId());
        return ResponseEntity.ok(favorites);
    }


    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(
            @RequestHeader HttpHeaders headers,
            @RequestParam UUID propertyId) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);
        propertyDeleterApi.removeFavorite(user.getId(), propertyId);
    }

}