package com.efubtoy.team1.auth.jwt;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.repository.AccountRepository;
import com.efubtoy.team1.auth.kakoLogin.CustomUserDetails;
import com.efubtoy.team1.exception.JWTAuthenticationException;
import com.efubtoy.team1.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private JWTUtils jwtUtils;
    private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Authorization header 내용 가져오기
        String authorization = request.getHeader("Authorization");

        try {
            // 1. 토큰 유무 확인
            if (authorization==null || !authorization.startsWith("Bearer ")){
                throw new JWTAuthenticationException("토큰이 없습니다. 로그인을 다시 진행합니다.");
            }

            String token = authorization.split(" ")[1];

            // 2. 토큰 기한 만료 여부 확인
            if (jwtUtils.isExpired(token)){
                throw new JWTAuthenticationException("토큰의 기한이 만료되었습니다. 다시 로그인을 진행하십시오.");
            }

            // 3. spring security context에 UserDetails 저장하기
            String email = jwtUtils.getEmail(token);
            Account account = accountRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("회원가입되어있지 않은 사용자입니다. 회원가입을 진행하십시오."));
            CustomUserDetails userDetails = new CustomUserDetails(account);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }catch (JWTAuthenticationException e){
            System.out.println("여기??");
            //logger.error(e.getMessage());
        }

        System.out.println("여기로 왔습니다");
        filterChain.doFilter(request,response);

    }
}
