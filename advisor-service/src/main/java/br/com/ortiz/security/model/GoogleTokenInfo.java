package br.com.ortiz.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GoogleTokenInfo implements Serializable {

    @JsonProperty("issued_to")
    private String issuedTo;
    private String audience;
    @JsonProperty("user_id")
    private String userId;
    private String scope;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    private String email;
    @JsonProperty("verified_email")
    private String verifiedEmail;
    @JsonProperty("access_type")
    private String accessType;
}
