package com.efubtoy.team1.auth.kakoLogin;

import com.efubtoy.team1.account.dto.AccountRequestDTO;
import com.efubtoy.team1.auth.dto.LoginResponseDTO;
import com.efubtoy.team1.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    private final KaKaoLoginService kaKaoLoginService;
    private final JWTUtils jwtUtils;

    @GetMapping("/login")
    public ResponseEntity<String> kakaoLogin(){
        String oauth2_url="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUri;
        return ResponseEntity.ok().body(oauth2_url);
    }

    @GetMapping("/oauth2/kakao")
    public LoginResponseDTO kakaoLoginCallback(@RequestParam("code") String code) throws IOException {
        System.out.println("LoginController.kakaoLoginCallback");

        String accessToken=kaKaoLoginService.getAccessTokenFromKakao(clientId,code);
        AccountRequestDTO userInfo=kaKaoLoginService.getUserInfo(accessToken);
        String userAccessToken = "Bearer "+jwtUtils.createToken(userInfo);
        String userRefreshToken= jwtUtils.createRefreshToken(userInfo);

        return LoginResponseDTO.builder()
                .userinfo(userInfo)
                .accessToken(userAccessToken)
                .refreshToken(userRefreshToken)
                .build();
    }
}
