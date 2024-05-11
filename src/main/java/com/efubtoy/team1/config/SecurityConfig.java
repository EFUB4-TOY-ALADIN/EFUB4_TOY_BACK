package com.efubtoy.team1.config;

import com.efubtoy.team1.account.repository.AccountRepository;
import com.efubtoy.team1.auth.jwt.JWTAuthenticationEntryPoint;
import com.efubtoy.team1.auth.jwt.JWTExceptionFilter;
import com.efubtoy.team1.auth.jwt.JWTFilter;
import com.efubtoy.team1.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTUtils jwtUtils;
    private final AccountRepository accountRepository;

    //swagger 필터 안타게
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> {
            web.ignoring()
                    .requestMatchers("/swagger-ui/**S",
                            "/swagger-resources/**",
                            "/v3/api-docs/**");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf((csrf)->csrf
                        .disable())
                .cors((auth)->auth
                        .configurationSource(corsConfiguration()))
                .httpBasic((basic)->basic
                        .disable())
                .authorizeHttpRequests((request)->request
                        .requestMatchers("/accounts/join","/login","/oauth2/kakao").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling((ex)->ex
                        .authenticationEntryPoint(jwtAuthenticationEntry()))
                .addFilterBefore(new JWTFilter(jwtUtils, accountRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTExceptionFilter(), JWTFilter.class)
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration=new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public JWTAuthenticationEntryPoint jwtAuthenticationEntry(){
        return new JWTAuthenticationEntryPoint();
    }

}
