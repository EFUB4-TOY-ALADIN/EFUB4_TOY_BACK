package com.efubtoy.team1.oauth;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.dto.LoginAccountResponse;
import com.efubtoy.team1.account.repository.AccountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KaKaoLoginService {

    private AccountRepository accountRepository;

    //카카오 서버에서 인증 토큰을 받아오는 메소드입니다.
    public String getAccessTokenFromKakao(String clientId, String code) throws IOException {
        // KAKAO 서버에 인증 토큰 발급 요청
        String reqURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id="+clientId+"&code=" + code;
        URL url=new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        //응답 받기
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line="";
        String result="";

        while ((line=br.readLine())!=null){
            result+=line;
        }

        ObjectMapper objectMapper= new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
        });

        //토큰 빼기
        String accessToken=(String) jsonMap.get("access_token");
        String refreshToken=(String) jsonMap.get("refresh_token");
        String scope = (String) jsonMap.get("scope");

        log.info("Access Token : " + accessToken);
        log.info("Refresh Token : " + refreshToken);
        log.info("Scope : " + scope);

        return accessToken;

    }

    public LoginAccountResponse getUserInfo(String accessToken) throws IOException{
        // kakao 서버에 access token으로 사용자 정보 요청
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer "+accessToken);
        conn.setRequestProperty("Content-type"," application/x-www-form-urlencoded;charset=utf-8");


        //응답 받기
        int responseCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line="";
        String result="";

        while((line=br.readLine())!=null){
            result+=line;
        }

        //응답에서 사용자 정보 추출
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>(){ });

        Map<String, Object> properties = (Map<String, Object>)jsonMap.get("properties");
        Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

        String nickname = properties.get("nickname").toString();
        String email = kakao_account.get("email").toString();

        //email 확인
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Account not found"));

        LoginAccountResponse dto = LoginAccountResponse.builder()
                .nickname(account.getNickname())
                .email(account.getEmail())
                .build();

        return dto;

    }
}
