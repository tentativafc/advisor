package br.com.ortiz.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetails implements Serializable {
    @JsonProperty("google_user_id")
    private String googleUserId;
    private String email;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
}
