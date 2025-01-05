package com.lubricentro.repuestosrungue.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
public class WebAuthorization {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de autorización
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/web/puntoventa.html").hasAuthority("ADMIN")
                                .requestMatchers("/api/ventas/todas").hasAuthority("ADMIN")
                                .requestMatchers("/api/ventas/crear").hasAuthority("ADMIN")
                                .requestMatchers("/api/productos").hasAuthority("ADMIN")
                                .anyRequest().permitAll()  // Permitir el acceso a cualquier otra URL no definida
                )
                .formLogin(formLogin ->
                        formLogin
                                .usernameParameter("user")
                                .passwordParameter("password")
                                .loginPage("/api/login")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/api/logout")
                                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                )
                .csrf().disable()  // Deshabilitar CSRF
                .headers().frameOptions().disable();  // Deshabilitar frameOptions para permitir acceso a h2-console

        return http.build();
    }
}