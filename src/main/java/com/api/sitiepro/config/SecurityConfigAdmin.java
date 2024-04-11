package com.api.sitiepro.config;

import com.api.sitiepro.filter.JwtAuthenticationFilterAdmin;
import com.api.sitiepro.service.UsuariosService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigAdmin {

    private final UsuariosService usuariosService;
    private final JwtAuthenticationFilterAdmin jwtAuthenticationFilterAdmin;
    private final CustomAccesDeniedHandlerAdmin accessDeniedHandlerAdmin;
    private final CustomLogoutHandlerAdmin customLogoutHandlerAdmin;

    public SecurityConfigAdmin(UsuariosService usuariosService,
                               JwtAuthenticationFilterAdmin jwtAuthenticationFilterAdmin,
                               CustomAccesDeniedHandlerAdmin accessDeniedHandlerAdmin,
                               CustomLogoutHandlerAdmin customLogoutHandlerAdmin) {
        this.usuariosService = usuariosService;
        this.jwtAuthenticationFilterAdmin = jwtAuthenticationFilterAdmin;
        this.accessDeniedHandlerAdmin = accessDeniedHandlerAdmin;
        this.customLogoutHandlerAdmin = customLogoutHandlerAdmin;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/inicio/**", "/registro/**")
                                .permitAll()
                                .requestMatchers("/admin_admin/**").hasAnyAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(usuariosService)
                .exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandlerAdmin)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilterAdmin, UsernamePasswordAuthenticationFilter.class)
                .logout(l->l.logoutUrl("/logout_admin")
                        .addLogoutHandler(customLogoutHandlerAdmin)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()
                        ))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
