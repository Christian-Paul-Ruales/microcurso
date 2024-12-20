package com.christian.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    /**
     * Pendejo del curso
     * */
    @Bean
    public SecurityWebFilterChain filterWebChain(ServerHttpSecurity httpSecurity){
        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults());

        return httpSecurity.build();
    }

    /***
     * Ejemplo web :)
     * */
    /**
     * @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/**").authenticated()
        )
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(httpSecuritySessionManagmetConfigurer ->
            httpSecuritySessionManagmetConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class
        );
        return httpSecurity.build();
    }*/
}
