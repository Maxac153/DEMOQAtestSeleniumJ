package api.demoqa.rest_assured.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserResponse(
        String userID,
        String username,
        List<Object> books
) {
}
