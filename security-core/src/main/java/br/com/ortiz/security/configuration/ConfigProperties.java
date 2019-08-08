package br.com.ortiz.security.configuration;

public class ConfigProperties {

    private String googleTokenInfoServiceUrl;
    private String[] urlsNonSecured;

    public String getGoogleTokenInfoServiceUrl() {
        return googleTokenInfoServiceUrl;
    }

    public void setGoogleTokenInfoServiceUrl(String googleTokenInfoServiceUrl) {
        this.googleTokenInfoServiceUrl = googleTokenInfoServiceUrl;
    }

    public String[] getUrlsNonSecured() {
        return urlsNonSecured;
    }

    public void setUrlsNonSecured(String[] urlsNonSecured) {
        this.urlsNonSecured = urlsNonSecured;
    }
}
