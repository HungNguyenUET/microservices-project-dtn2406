package vti.dtn.auth_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import vti.dtn.auth_service.entity.Role;
import vti.dtn.auth_service.oauth2.handler.OAuth2AuthenticationFailureHandler;
import vti.dtn.auth_service.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import vti.dtn.auth_service.oauth2.repository.HttpCookieOAuthorizationRequestRepository;
import vti.dtn.auth_service.oauth2.service.CustomOAuth2UserService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {
            "/oauth2/redirect",
            "/oauth2/authorize",
            "/oauth2/authorise",
            "/oauth2/authorize/github",
            "/oauth2/callback/github",
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh-token"
    };

    private final CustomOAuth2UserService customOAuth2UserService;
    private final HttpCookieOAuthorizationRequestRepository httpCookieOAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                        .permitAll()
                        .requestMatchers("/api/v1/accounts").hasAnyRole(Role.ADMIN.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorise")
                .authorizationRequestRepository(httpCookieOAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        return http.build();
    }
}
