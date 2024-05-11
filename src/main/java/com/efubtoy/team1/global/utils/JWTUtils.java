package com.efubtoy.team1.global.utils;

import com.efubtoy.team1.domain.account.dto.AccountRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {
    private Key key;
    private Key rKey;

    @Autowired
    public JWTUtils(@Value("${spring.jwt.secret}")String secretKey,@Value("${spring.jwt.refresh-secret}")String refreshKey ){
        byte[] decodeKey = Decoders.BASE64.decode(secretKey);
        byte[] decodeRKey = Decoders.BASE64.decode(refreshKey);
        key= Keys.hmacShaKeyFor(decodeKey);
        rKey=Keys.hmacShaKeyFor(decodeRKey);
    }

    public String createToken(AccountRequestDTO dto){
        Claims claims= Jwts.claims();
        claims.put("nickname",dto.getNickname());
        claims.put("email",dto.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*2*1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(AccountRequestDTO dto){
        Claims claims= Jwts.claims();
        claims.put("nickname",dto.getNickname());
        claims.put("email",dto.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*24*14*1000))
                .signWith(rKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isExpired(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);

    }
}
