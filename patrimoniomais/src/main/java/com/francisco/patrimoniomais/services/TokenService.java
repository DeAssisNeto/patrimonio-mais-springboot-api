package com.francisco.patrimoniomais.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.francisco.patrimoniomais.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${JWT_SECRET:my-secret-key}")
    private String secret;

    public String generateToken(UserModel user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("patrimoniomais")
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
            return token;

        }catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar o token", exception);

        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("patrimoniomais")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusMinutes(3).toInstant(ZoneOffset.of("-03:00"));
    }

}
