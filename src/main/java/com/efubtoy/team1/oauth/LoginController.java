package com.efubtoy.team1.oauth;

import com.efubtoy.team1.account.dto.LoginAccountResponse;
import com.efubtoy.team1.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    private KaKaoLoginService kaKaoLoginService;
    private JWTUtils jwtUtils;

    @GetMapping("/login")
    public ResponseEntity<String> kakaoLogin(){
        String oauth2_url="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUri;
        return ResponseEntity.ok().body(oauth2_url);
    }

//    @GetMapping("/oauth2/kakao")
//    public LoginAccountResponse kakaoLoginCallback(@RequestParam("code") String code) throws IOException {
//        String accessToken=kaKaoLoginService.getAccessTokenFromKakao(clientId,code);
//        LoginAccountResponse userInfo=kaKaoLoginService.getUserInfo(accessToken);
//        String userAccessToken = jwtUtils.createToken(userInfo);
//        return
//    }
}
