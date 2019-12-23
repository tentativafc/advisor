package br.com.ortiz.security.configuration;

import lombok.Data;

@Data
public class ConfigProperties {
    private String googleTokenInfoServiceUrl;
    private String[] urlsNonSecured;
}
