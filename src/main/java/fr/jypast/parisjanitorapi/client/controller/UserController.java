package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.user.UserCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.user.UserDto;
import fr.jypast.parisjanitorapi.client.dto.user.UserLogRequest;
import fr.jypast.parisjanitorapi.client.mapper.UserDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.UserNotFoundException;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.user.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final AuthVerifierService authVerifierService;

    private final UserCreatorApi userCreatorApi;

    private final UserUpdaterApi userUpdaterApi;

    private final UserFinderApi userFinderApi;

    private final UserLoggerApi userLoggerApi;

    private final UserDeleterApi userDeleterApi;

    private final UserVerifierApi userVerifierApi;

    private final TokenControllerService tokenControllerService;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserDto> getUsers() {
        return userFinderApi.findAll()
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/me")
    @ResponseStatus(OK)
    public UserDto getMyProfil(@RequestHeader HttpHeaders headers) {
        return UserDtoMapper.toDto(tokenControllerService.getUserByToken(authVerifierService.getToken(headers)));
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(OK)
    public UserDto getUserById(@PathVariable String id) {
        return userFinderApi.findById(UuidValidator.validate(id))
                .map(UserDtoMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(OK)
    public UserDto getUserByEmail(@PathVariable String email) {
        return userFinderApi.findByEmail(email)
                .map(UserDtoMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDto createUser(@Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userCreatorApi.create(
                        UserDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public UserDto createOrUpdateUser(@PathVariable String id, @Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userCreatorApi.createWithId(
                        UserDtoMapper.creationRequestToDomain(request),
                        UuidValidator.validate(id)
                )
        );
    }

    @PatchMapping
    @ResponseStatus(OK)
    public UserDto patchUser(@RequestHeader HttpHeaders headers, @Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userUpdaterApi.update(
                        authVerifierService.getToken(headers),
                        UserDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteUserByToken(@RequestHeader HttpHeaders headers) {
        userDeleterApi.deleteByToken(authVerifierService.getToken(headers));
    }


    @PostMapping(path = "/log")
    @ResponseStatus(OK)
    public UserDto login(@RequestBody UserLogRequest log) {
        return UserDtoMapper.toDto(
                userLoggerApi.login(
                        log.email(),
                        log.password()));
    }

    @GetMapping("/verify")
    @ResponseStatus(OK)
    public String verifyUser(
            @RequestParam String email,
            @RequestParam String code
    ) {
        return userVerifierApi.verify(email, code) ? "Vérification effectué" : "Erreur lors de la vérification";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
