package br.com.ortiz.security;

import br.com.ortiz.security.configuration.ConfigProperties;
import br.com.ortiz.security.filters.JwtAuthenticationFilter;
import br.com.ortiz.security.services.GoogleTokenService;
import br.com.ortiz.security.services.JwtService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableCassandraRepositories
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("Configuring security...");

        String[] urlsNonSecured = ArrayUtils.add(configProperties().getUrlsNonSecured(), "/service-instances/*");

        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(urlsNonSecured)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), getApplicationContext()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // cria uma conta default
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");
    }

    @Bean
    @ConfigurationProperties(prefix = "ortiz.security")
    public ConfigProperties configProperties() {
        return new ConfigProperties();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public GoogleTokenService googleTokenService() {
        return new GoogleTokenService();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }


}
