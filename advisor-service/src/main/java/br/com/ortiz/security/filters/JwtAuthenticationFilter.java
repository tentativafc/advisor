package br.com.ortiz.security.filters;

import br.com.ortiz.security.model.User;
import br.com.ortiz.security.services.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtService jwtService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        super(authenticationManager);
        this.jwtService = ctx.getBean(JwtService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Security filter...");
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtService.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(JwtService.TOKEN_PREFIX)) {
            Optional<User> userFromToken = jwtService.getUserFromToken(token);
            if (userFromToken.isPresent()) {
                logger.info("Secured...");
                return new UsernamePasswordAuthenticationToken(userFromToken.get().getId(), null, Collections.emptyList());
            }
        }
        return null;
    }
}
