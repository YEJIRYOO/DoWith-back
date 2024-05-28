package com.project.doWith.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component //Bean 등록 없이 사용 가능
public class JwtUtil {
    private final Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.expiration_time}")long accessTokenExpTime
    ){
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        this.key= Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime=accessTokenExpTime;
    }

    //Access Token 생성
    public String createAccessToken(CustomUser InfoDto member){
        return
    }
}
