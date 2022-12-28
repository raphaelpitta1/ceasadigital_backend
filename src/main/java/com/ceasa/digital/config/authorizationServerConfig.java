package com.ceasa.digital.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableAuthorizationServer
public class authorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    

    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String singKey;
    @Bean
    public TokenStore tokenStore(){

        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){

        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();

        tokenConverter.setSigningKey(singKey);

        return tokenConverter;

    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter())
        .authenticationManager(authenticationManager);
    }




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        
        clients.inMemory().withClient("99527b18-29c2-4598-94a6-d460e606abf0").secret("8d1e2298-4da8-41d7-92ba-8b99a3152e19").scopes("read","write").authorizedGrantTypes("password").accessTokenValiditySeconds(3600);

        
}
}