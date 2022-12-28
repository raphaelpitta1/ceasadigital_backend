package com.ceasa.digital.config;

import javax.persistence.Basic;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ceasa.digital.services.userService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private userService userService;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            // ...
            .cors(cors -> cors.disable());
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{

            return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    
    @Bean
    public PasswordEncoder passwordEncoder(){

       
        return NoOpPasswordEncoder.getInstance();
    }

        
}
