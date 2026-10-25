
package com.studentstudyplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security settings for the Student Study Planner application.
 *
 * This class uses Spring Security to manage user authentication,
 * password encryption, and access to application pages.
 *
 * It allows users to access the registration and login pages
 * without authentication while protecting other application pages.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Creates a password encoder for securely hashing passwords.
     *
     * Uses BCryptPasswordEncoder to hash passwords before they
     * are stored and to verify passwords during authentication.
     *
     * @return a PasswordEncoder that uses BCrypt hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the application's HTTP security settings.
     *
     * Allows public access to the registration page, login page,
     * CSS files, and JavaScript files. All other requests require
     * the user to be authenticated.
     *
     * Configures the custom login page, login processing URL,
     * successful login redirect, failed login redirect,
     * and logout behavior.
     *
     * @param http the HttpSecurity object used to configure
     *             authentication and access rules
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while building
     *                   the security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(loginForm -> loginForm
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login"));
        
        return http.build();
    }
}

