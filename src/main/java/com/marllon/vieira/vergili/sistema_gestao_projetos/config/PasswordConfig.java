package com.marllon.vieira.vergili.sistema_gestao_projetos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {


    @Bean
    /**Método para encriptar a senha usando Bcrypt**/
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
