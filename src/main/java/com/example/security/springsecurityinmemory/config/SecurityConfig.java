package com.example.security.springsecurityinmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails testUser = User.builder()
                .username("testUser")
                .password("{noop}testUser")
                .roles("TEST").build();

        UserDetails devUser = User.builder()
                .username("devUser")
                .password("{noop}devUser")
                .roles("DEVELOPER").build();

        UserDetails managerUser = User.builder()
                .username("managerUser")
                .password("{noop}managerUser")
                .roles("DEVELOPER","MANAGER").build();

        return new InMemoryUserDetailsManager(testUser, devUser, managerUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET,"/api/v1/inMemory").hasAnyRole("TEST","DEVELOPER")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/inMemory").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.PUT ,"/api/v1/inMemory").hasAnyRole("DEVELOPER","MANAGER")
                        .requestMatchers(HttpMethod.DELETE ,"/api/v1/inMemory").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
