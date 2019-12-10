package br.com.ortiz.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSessionDetails extends UserDetails {
    @JsonProperty("google_access_token")
    private String googleAccessToken;
    @JsonProperty("access_token")
    private String accessToken;
}
