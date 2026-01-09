package com.marllon.vieira.vergili.sistema_gestao_projetos.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenCreator {

    private final String TOKEN = "key";
    private final long TIME_EXPIRES = 450000;



    public String createToken(User user){
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(new Date())
                .withExpiresAt(Instant.now().plus(TIME_EXPIRES, ChronoUnit.MILLIS))
                .sign(Algorithm.HMAC256(TOKEN));
    }


    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(TOKEN)).build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }


    public String getEmailFromToken(String token){

        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(TOKEN))
                    .build()
                    .verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }

    public String refreshToken(String oldToken) {

        try{
            //Verificar se o token antigo é válido
            DecodedJWT decoderToken = JWT.require(Algorithm.HMAC256(TOKEN))
                    .build()
                    .verify(oldToken);

            //Extrair informações do token antigo
            String email = decoderToken.getSubject();
            Long userId = decoderToken.getClaim("id").asLong();
            String role = decoderToken.getClaim("role").asString();

            //Re-criar o novo token com as mesmas informações
            return JWT.create()
                    .withSubject(email)
                    .withClaim("id", userId)
                    .withClaim("role", role)
                    .withIssuedAt(new java.util.Date())
                    .withExpiresAt(java.util.Date.from(Instant.now().plus(TIME_EXPIRES, ChronoUnit.MILLIS)))
                    .sign(Algorithm.HMAC256(TOKEN));
        }catch (JWTVerificationException e){
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }
}
