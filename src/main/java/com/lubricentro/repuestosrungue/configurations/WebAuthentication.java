package com.lubricentro.repuestosrungue.configurations;

import com.lubricentro.repuestosrungue.classes.Usuario;
import com.lubricentro.repuestosrungue.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UsuarioRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Usuario user = userRepository.findByUsername(inputName);
            if (user != null) {
                if (user.getUsername().contains("admin")) {
                    return new User(user.getUsername(), user.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }else{
                    return new User(user.getUsername(), user.getPassword(),
                            AuthorityUtils.createAuthorityList("VENDEDOR"));
                }
            } else {
                throw new UsernameNotFoundException("Usuario desconocido: " + inputName);
            }
        });
    }
}