package ortiz.advisor.auth.controllers;

import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ortiz.security.model.GoogleTokenInfo;
import ortiz.security.model.User;
import ortiz.security.model.UserSessionDetails;
import ortiz.security.model.repository.UserRepository;
import ortiz.security.services.GoogleTokenService;
import ortiz.security.utils.JwtUtil;


@RestController
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private GoogleTokenService googleTokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<UserSessionDetails> login(@RequestBody UserSessionDetails userSessionDetails) {

        logger.info("Validating the user session...");

        User user = userRepository.findByGoogleUserId(userSessionDetails.getGoogleUserId());

        try {
            GoogleTokenInfo googleTokenInfo = googleTokenService.validateToken(userSessionDetails.getGoogleAccessToken());

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


}
