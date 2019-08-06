package ortiz.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ortiz.security.configuration.ConfigProperties;
import ortiz.security.model.GoogleTokenInfo;

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

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }
}
