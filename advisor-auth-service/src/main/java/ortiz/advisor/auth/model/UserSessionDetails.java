package ortiz.advisor.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSessionDetails extends UserDetails {
    @JsonProperty("google_access_token")
    private String googleAccessToken;

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public void setGoogleAccessToken(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }
}
