package com.bootcamp.stock.infrastructure.configuration;

import com.bootcamp.stock.infrastructure.security.CustomAccessDeniedHandler;
import com.bootcamp.stock.infrastructure.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.bootcamp.stock.domain.utils.constants.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/categoria/save-category").hasAnyRole(ROL_ADMIN)
                        .requestMatchers("categoria/get-categories").permitAll()
                        .requestMatchers("/marca/save-marca").hasRole(ROL_ADMIN)
                        .requestMatchers("/marca/get-marcas").permitAll()
                        .requestMatchers("/articulo/save-articulo").hasRole(ROL_ADMIN)
                        .requestMatchers("/articulo/agregar-cantidad-articulo").hasRole(ROL_AUX_BODEGA)
                        .requestMatchers("/articulo/get-articulos").permitAll()
                        .requestMatchers("/articulo/articulo-info/*").permitAll()
                        .requestMatchers("/articulo/get-articulos-by-ids").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
