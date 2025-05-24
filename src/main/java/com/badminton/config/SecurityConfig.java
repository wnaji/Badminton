package com.badminton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.enabled:false}")
    private boolean securityEnabled;

    @Value("${api.key.header:X-API-Key}")
    private String apiKeyHeader;

    @Value("${api.key.value:}")
    private String apiKeyValue;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if (securityEnabled) {
            http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").authenticated()
                .and()
                .addFilterBefore(new ApiKeyAuthFilter(apiKeyHeader, apiKeyValue), BasicAuthenticationFilter.class);
        } else {
            http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll();
        }
        
        return http.build();
    }
} 