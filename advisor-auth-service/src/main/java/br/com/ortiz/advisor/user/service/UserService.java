package br.com.ortiz.advisor.user.service;

import br.com.ortiz.security.model.User;
import br.com.ortiz.security.model.UserSessionDetails;
import br.com.ortiz.security.repositories.UserRepository;
import br.com.ortiz.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtUtil;

    public UserSessionDetails save(User user) {
        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setEmail(user.getEmail());
        userRepository.save(newUser);
        String accessToken = jwtUtil.getTokenFromUser(user);
        UserSessionDetails userSessionDetails = new UserSessionDetails();
        userSessionDetails.setAccessToken(accessToken);
        return userSessionDetails;
    }

    public UserSessionDetails login(User user) throws IllegalAccessException {
        final User loadedUser = userRepository.findByEmail(user.getEmail());

        if (loadedUser == null || loadedUser.getPassword().equals(loadedUser.getPassword())) {
            throw new IllegalAccessException("Usuário e/ou inválidos.");
        }

        String accessToken = jwtUtil.getTokenFromUser(loadedUser);
        UserSessionDetails userSessionDetails = new UserSessionDetails();
        userSessionDetails.setAccessToken(accessToken);
        return userSessionDetails;
    }

}
