package com.efubtoy.team1.global.config;

import com.efubtoy.team1.domain.account.repository.AccountRepository;
import com.efubtoy.team1.domain.auth.jwt.JWTAuthenticationEntryPoint;
import com.efubtoy.team1.domain.auth.jwt.JWTExceptionFilter;
import com.efubtoy.team1.domain.auth.jwt.JWTFilter;
import com.efubtoy.team1.global.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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
                            //"/**",
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
                        .requestMatchers("/accounts/join","/login","/oauth2/kakao","/books/**","/goods/**" , "/records/**" , "/review/**","/records/**" , "/search**").permitAll()
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

        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedOrigin("https://localhost:3000");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PATCH");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedHeader("*");

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public JWTAuthenticationEntryPoint jwtAuthenticationEntry(){
        return new JWTAuthenticationEntryPoint();
    }

}