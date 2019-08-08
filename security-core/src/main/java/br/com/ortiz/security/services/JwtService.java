package br.com.ortiz.security.services;

import br.com.ortiz.security.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Optional;


public class JwtService {

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "user_email";
    private static final String GOOGLE_USER_ID = "google_user_id";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    private String tokenSecret = "aloha";

    public Optional<User> getUserFromToken(String token) {
        User user = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(tokenSecret)).build();
            DecodedJWT decodedJWT = verifier.verify(token.replace(TOKEN_PREFIX, ""));
            String userId = decodedJWT.getClaim(USER_ID).asString();
            String email = decodedJWT.getClaim(USER_EMAIL).asString();
            String googleUserId = decodedJWT.getClaim(GOOGLE_USER_ID).asString();
            user = new User();
            user.setId(userId);
            user.setGoogleUserId(googleUserId);
            user.setEmail(email);
        } catch (Exception exc) {
            // Invalid token.
        }
        return Optional.ofNullable(user);
    }

    public Optional<String> getUserIdFromToken(String token) {
        return getUserFromToken(token).map((User user) -> user.getId());
    }

    public String getTokenFromUser(User user) {
        // TODO Expires
        return JWT.create().withClaim(USER_ID, user.getId())
                .withClaim(USER_EMAIL, user.getEmail())
                .withClaim(GOOGLE_USER_ID, user.getGoogleUserId())
                .sign(Algorithm.HMAC512(tokenSecret));
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}