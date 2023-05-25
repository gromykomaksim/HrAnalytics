package com.example.hranalytics.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.hranalytics.domain.consts.Endpoints.SWAGGER_ENDPOINTS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Order(1)
public class SwaggerSecurityConfig {
    @Value("${swagger.username}")
    private String username;
    @Value("${swagger.password}")
    private String password;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        UserDetails user = User.builder()
                .username(username)
                .password(password)
                .roles("user")
                .build();

        return http
                .userDetailsService(new InMemoryUserDetailsManager(user))
                .requestMatchers()
                .antMatchers(SWAGGER_ENDPOINTS).and()
                .authorizeRequests().anyRequest().authenticated().and()
                .httpBasic().and()
                .build();
    }
}
