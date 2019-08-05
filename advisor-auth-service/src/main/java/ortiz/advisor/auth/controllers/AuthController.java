package ortiz.advisor.auth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ortiz.advisor.auth.model.GoogleTokenInfo;
import ortiz.advisor.auth.model.User;
import ortiz.advisor.auth.model.UserSessionDetails;
import ortiz.advisor.auth.model.repository.UserRepository;
import ortiz.advisor.auth.util.JwtUtil;

@RestController
@ConfigurationProperties(prefix = "ortiz.auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private String googleTokenServiceInfoUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<UserSessionDetails> login(@RequestBody UserSessionDetails userSessionDetails) {

        logger.info("Invoking Google Token Service Validation: {}", googleTokenServiceInfoUrl);

        RestTemplate restTemplate = new RestTemplate();

        User user = userRepository.findByGoogleUserId(userSessionDetails.getGoogleUserId());

        try {
            ResponseEntity<GoogleTokenInfo> response = restTemplate.getForEntity(googleTokenServiceInfoUrl, GoogleTokenInfo.class, userSessionDetails.getGoogleAccessToken());

            GoogleTokenInfo googleTokenInfo = response.getBody();

            if (user == null) {
                user = new User();
                user.setId(userSessionDetails.getGoogleUserId());
                user.setEmail(googleTokenInfo.getEmail());
                user.setGoogleUserId(userSessionDetails.getGoogleUserId());
                userRepository.save(user);
            }

            String accessToken = jwtUtil.getTokenFromUser(user);
            user.setAccessToken(accessToken);
            userRepository.save(user);
            userSessionDetails.setAccessToken(accessToken);

            return new ResponseEntity<>(userSessionDetails, HttpStatus.OK);

        } catch (HttpClientErrorException | HttpServerErrorException exc) {

            if (user != null) {
                user.setAccessToken(null);
                userRepository.save(user);
            }

            throw new ResponseStatusException(exc.getStatusCode(), exc.getLocalizedMessage());
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
        }
    }

    @PostMapping("/invalidate")
    public ResponseEntity<UserSessionDetails> logout(@RequestBody UserSessionDetails userSessionDetails) {
        return new ResponseEntity<>(userSessionDetails, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    public String getGoogleTokenServiceInfoUrl() {
        return googleTokenServiceInfoUrl;
    }

    public void setGoogleTokenServiceInfoUrl(String googleTokenServiceInfoUrl) {
        this.googleTokenServiceInfoUrl = googleTokenServiceInfoUrl;
    }
}
