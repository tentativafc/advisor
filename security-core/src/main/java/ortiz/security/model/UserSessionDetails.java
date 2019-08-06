package ortiz.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSessionDetails extends UserDetails {
    @JsonProperty("google_access_token")
    private String googleAccessToken;
    @JsonProperty("access_token")
    private String accessToken;

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public void setGoogleAccessToken(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
