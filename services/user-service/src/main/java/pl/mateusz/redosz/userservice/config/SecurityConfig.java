package pl.mateusz.redosz.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.mateusz.redosz.userservice.config.properties.JwtProperties;
import pl.mateusz.redosz.userservice.config.properties.UrlsProperties;
import pl.mateusz.redosz.userservice.security.CustomAccessDeniedHandler;
import pl.mateusz.redosz.userservice.security.CustomUserDetailsService;
import pl.mateusz.redosz.userservice.security.jwt.JwtAuthFilter;
import pl.mateusz.redosz.userservice.security.jwt.JwtGenerator;
import pl.mateusz.redosz.userservice.security.jwt.JwtManager;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UrlsProperties urlsProperties;
    private final CustomUserDetailsService customUserDetailsService;
    private final RequestMatcher publicPathsMatcher = new OrRequestMatcher(
            PathPatternRequestMatcher.withDefaults().matcher("/login/**"),
            PathPatternRequestMatcher.withDefaults().matcher("/register/**")
    );
    private final RequestMatcher privatePathsMatcher = new NegatedRequestMatcher(publicPathsMatcher);


    @Bean
    @Order(1)
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http, JwtProperties jwtProperties, CorsConfigurationSource corsConfigurationSource) {
        return http
                .securityMatcher(publicPathsMatcher)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(urlsProperties.getLogoutUrl())
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(200))
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies(jwtProperties.getTokenName())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .httpBasic(HttpBasicConfigurer::disable)
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain privateSecurityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter, CustomAccessDeniedHandler customAccessDeniedHandler, CorsConfigurationSource corsConfigurationSource) {
        return http
                .securityMatcher(privatePathsMatcher)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, LogoutFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthenticationFilter(JwtGenerator jwtGenerator, JwtManager jwtManager,
                                                 JwtProperties jwtProperties) {
        return new JwtAuthFilter(
                jwtGenerator,
                customUserDetailsService,
                jwtManager,
                jwtProperties
        );
    }

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilterRegistration(JwtAuthFilter jwtAuthFilter) {
        FilterRegistrationBean<JwtAuthFilter> registrationBean = new FilterRegistrationBean<>(jwtAuthFilter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}