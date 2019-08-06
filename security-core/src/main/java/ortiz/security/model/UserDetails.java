package ortiz.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserDetails implements Serializable {
    @JsonProperty("google_user_id")
    private String googleUserId;
    private String email;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;

    public String getGoogleUserId() {
        return googleUserId;
    }

    public void setGoogleUserId(String googleUserId) {
        this.googleUserId = googleUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
