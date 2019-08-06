package ortiz.security.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ortiz.security.filters.JwtAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("Configuring security...");

        if (ArrayUtils.isNotEmpty(configProperties().getUrlsNonSecured())) {

            http.cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers(configProperties().getUrlsNonSecured())
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager(), getApplicationContext()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        } else {

            http.cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager(), getApplicationContext()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
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


}
