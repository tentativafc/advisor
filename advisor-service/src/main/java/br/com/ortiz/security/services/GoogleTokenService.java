package br.com.ortiz.security.services;

import br.com.ortiz.security.configuration.ConfigProperties;
import br.com.ortiz.security.model.GoogleTokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleTokenService {

    private Logger logger = LoggerFactory.getLogger(GoogleTokenService.class);

    @Autowired
    private ConfigProperties configProperties;

    public GoogleTokenInfo validateToken(String googleToken) {

        RestTemplate restTemplate = new RestTemplate();

        logger.info("Invoking Google Token Service Validation: {}", configProperties.getGoogleTokenInfoServiceUrl());

        ResponseEntity<GoogleTokenInfo> response = restTemplate.getForEntity(configProperties.getGoogleTokenInfoServiceUrl(), GoogleTokenInfo.class, googleToken);
        GoogleTokenInfo googleTokenInfo = response.getBody();

        return googleTokenInfo;
    }

}
