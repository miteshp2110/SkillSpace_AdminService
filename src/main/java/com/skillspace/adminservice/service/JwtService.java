package com.skillspace.adminservice.service;

import com.skillspace.adminservice.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secret = "mySuperSecretKeyThatIsVeryLongSoThatItDoesNotCreateAProblemOfAnyKindToMe";

    private SecretKey getKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){

        Token token_status = tokenStatus(token);

        if(!token_status.isValid() || token_status.isExpired()){
            return "Invalid token";
        }

        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token){

        Token token_status = tokenStatus(token);
//        System.out.println(token_status);

        if(!token_status.isValid() || token_status.isExpired()){
            return "Invalid token";
        }

        final Claims claim = extractAllClaims(token);
        return claim.get("role", String.class);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public Token tokenStatus(String token){
        Token token_status = new Token();
        token_status.setToken(token);
        token_status.setValid(true);
        try{
            Claims claims = extractAllClaims(token);
        }
        catch(SignatureException | MalformedJwtException e){
            token_status.setToken(token);
            token_status.setValid(false);

            return token_status;
        }
        catch(ExpiredJwtException e){
            token_status.setToken(token);
            token_status.setExpired(true);
            token_status.setValid(true);
        }
        return token_status;
    }
}
