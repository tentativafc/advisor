package br.com.ortiz.advisor.user.controllers;

import br.com.ortiz.advisor.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import br.com.ortiz.security.model.User;
import br.com.ortiz.security.model.UserSessionDetails;
import br.com.ortiz.security.services.GoogleTokenService;
import br.com.ortiz.security.services.JwtService;


@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GoogleTokenService googleTokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserSessionDetails> register(@RequestBody User user) {
        logger.info("Registering user...");
        try {
            final UserSessionDetails userSessionDetails = userService.save(user);
            return new ResponseEntity<UserSessionDetails>(userSessionDetails, HttpStatus.OK);
        } catch (HttpClientErrorException | HttpServerErrorException exc) {
            throw new ResponseStatusException(exc.getStatusCode(), exc.getLocalizedMessage());
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserSessionDetails> login(@RequestBody User user) {
        logger.info("Registering user...");
        try {
            final UserSessionDetails userSessionDetails = userService.save(user);
            return new ResponseEntity<UserSessionDetails>(userSessionDetails, HttpStatus.OK);
        } catch (HttpClientErrorException | HttpServerErrorException exc) {
            throw new ResponseStatusException(exc.getStatusCode(), exc.getLocalizedMessage());
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
        }
    }

//    @PostMapping("/validate")
//    public ResponseEntity<UserSessionDetails> login(@RequestBody UserSessionDetails userSessionDetails) {
//
//        logger.info("Validating the user session...");
//
//        User user = userRepository.findByGoogleUserId(userSessionDetails.getGoogleUserId());
//
//        try {
//            GoogleTokenInfo googleTokenInfo = googleTokenService.validateToken(userSessionDetails.getGoogleAccessToken());
//
//            if (user == null) {
//                user = new User();
//                user.setId(UUID.randomUUID());
//                user.setEmail(googleTokenInfo.getEmail());
//                user.setGoogleUserId(userSessionDetails.getGoogleUserId());
//                userRepository.save(user);
//            }
//
//            String accessToken = jwtUtil.getTokenFromUser(user);
//            user.setAccessToken(accessToken);
//            userRepository.save(user);
//            userSessionDetails.setAccessToken(accessToken);
//
//            return new ResponseEntity<>(userSessionDetails, HttpStatus.OK);
//
//        } catch (HttpClientErrorException | HttpServerErrorException exc) {
//
//            if (user != null) {
//                user.setAccessToken(null);
//                userRepository.save(user);
//            }
//
//            throw new ResponseStatusException(exc.getStatusCode(), exc.getLocalizedMessage());
//        } catch (Exception exc) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
//        }
//    }
//
//    @PostMapping("/invalidate")
//    public ResponseEntity<UserSessionDetails> logout(@RequestBody UserSessionDetails userSessionDetails) {
//        User user = userRepository.findByGoogleUserId(userSessionDetails.getGoogleUserId());
//        user.setAccessToken(null);
//        userRepository.save(user);
//        userSessionDetails.setAccessToken(null);
//        return new ResponseEntity<>(userSessionDetails, HttpStatus.OK);
//    }
}
