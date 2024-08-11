package fr.jypast.parisjanitorapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserLogRequest(
        @NotNull @JsonProperty String pseudo,
        @NotNull @JsonProperty String password
) {

}
