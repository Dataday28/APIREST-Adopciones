package com.proyecto.adopcion.config;

import com.proyecto.adopcion.service.UserDetailServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // Filtro
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Endpoints publicos
                    http.requestMatchers(HttpMethod.GET, "/api/mascotas").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/tipomascota").permitAll();

                    // Endpoints privados
                    http.requestMatchers(HttpMethod.GET, "/api/usuarios").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/usuarios").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/api/usuarios").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/usuarios").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/usuarios").hasAuthority("DELETE");

                    http.requestMatchers(HttpMethod.POST, "/api/mascotas").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/api/mascotas").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/mascotas").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/mascotas").hasAuthority("DELETE");

                    http.requestMatchers(HttpMethod.POST, "/api/tipomascota").hasAuthority("CREATE");

                    http.requestMatchers(HttpMethod.GET, "/api/adopciones").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/adopciones").hasAuthority("CREATE");

                    // Resto de endpoints - No especificados
                    http.anyRequest().authenticated();
                })
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceSecurity userDetailServiceSecurity) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceSecurity);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
