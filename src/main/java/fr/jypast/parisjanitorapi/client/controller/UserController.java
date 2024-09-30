package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.user.*;
import fr.jypast.parisjanitorapi.client.mapper.UserDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.client.validator.UuidValidator;
import fr.jypast.parisjanitorapi.domain.functionnal.exception.user.UserNotFoundException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.PasswordEncoder;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.user.*;
import fr.jypast.parisjanitorapi.domain.port.out.EmailingSpi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private final EmailingSpi emailSenderSpi;

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

    @PutMapping("/update")
    @ResponseStatus(OK)
    public UserDto updateUser(@RequestHeader HttpHeaders headers, @Valid @RequestBody UserUpdateRequest request) {
        UUID token = authVerifierService.getToken(headers);
        User existingUser = tokenControllerService.getUserByToken(token);

        Optional<User> userWithSameEmail = userFinderApi.findByEmail(request.email().trim());

        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(existingUser.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        }

        User updatedUser = existingUser.withEmail(request.email().trim())
                .withLastName(request.lastName().trim())
                .withFirstName(request.firstName().trim())
                .withBirthday(request.birthday())
                .withPhoneNumber(request.phoneNumber() != null ? request.phoneNumber().trim() : null)
                .withRegion(request.region() != null ? request.region().trim() : null)
                .withAdresse1(request.adresse1() != null ? request.adresse1().trim() : null)
                .withAdresse2(request.adresse2() != null ? request.adresse2().trim() : null)
                .withProfilePicture(request.profilePicture() != null ? request.profilePicture().trim() : null);

        User savedUser = userUpdaterApi.update(token, updatedUser);

        return UserDtoMapper.toDto(savedUser);
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

    @PostMapping("/securityModifier")
    @ResponseStatus(OK)
    public void securityModifier(@RequestHeader HttpHeaders headers) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);

        emailSenderSpi.sendCodeVerifier(user, user.getVerificationCode());
    }

    @GetMapping("/verifyPasswordCode/{code}")
    @ResponseStatus(OK)
    public void verifyPasswordCode(@RequestHeader HttpHeaders headers, @PathVariable String code) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);

        if (user.getVerificationCode() == null || !user.getVerificationCode().equals(code.trim())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid verification code");
        }
    }

    @PatchMapping("/updatePassword")
    @ResponseStatus(OK)
    public void updatePassword(@RequestHeader HttpHeaders headers, @RequestBody PasswordVerificationRequest request) {
        UUID token = authVerifierService.getToken(headers);
        User existingUser = tokenControllerService.getUserByToken(token);

        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String hashedPassword = passwordEncoder.hashPassword(request.password());

        User updatedUser = existingUser.withPassword(hashedPassword);

        userUpdaterApi.updatePwd(token, updatedUser);
    }

    @PostMapping("/verifyPassword")
    @ResponseStatus(OK)
    public boolean verifyPassword(@RequestHeader HttpHeaders headers, @RequestBody PasswordVerificationRequest request) {
        UUID token = authVerifierService.getToken(headers);
        User user = tokenControllerService.getUserByToken(token);

        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String hashedPassword = passwordEncoder.hashPassword(request.password());

        return hashedPassword.equals(user.getPassword());
    }

    @GetMapping("/owner-info/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OwnerDto> getOwnerInfo(@PathVariable UUID id) {
        return userFinderApi.findById(id)
                .map(user -> new OwnerDto(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhoneNumber()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
