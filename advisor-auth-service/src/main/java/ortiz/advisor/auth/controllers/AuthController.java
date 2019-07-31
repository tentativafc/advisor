package ortiz.advisor.auth.controllers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ortiz.advisor.auth.model.GoogleTokenInfo;
import ortiz.advisor.auth.model.UserSessionDetails;

@RestController
@ConfigurationProperties(prefix = "ortiz.auth")
public class AuthController {

    private String googleTokenServiceInfoUrl;

    @PostMapping()
    public ResponseEntity<UserSessionDetails> login(@RequestBody UserSessionDetails userSessionDetails) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleTokenInfo> response = restTemplate.getForEntity(googleTokenServiceInfoUrl, GoogleTokenInfo.class, userSessionDetails.getGoogleAccessToken());
        if (response.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(userSessionDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.valueOf(response.getStatusCodeValue()));
        }
    }
}
