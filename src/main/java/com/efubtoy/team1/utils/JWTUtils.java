package com.efubtoy.team1.utils;

import com.efubtoy.team1.account.dto.AccountDTO;
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

    @Autowired
    public JWTUtils(@Value("${spring.jwt.secret}")String secretKey){
        byte[] decodeKey = Decoders.BASE64.decode(secretKey);
        key= Keys.hmacShaKeyFor(decodeKey);
    }

    public String createToken(AccountDTO dto){
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
