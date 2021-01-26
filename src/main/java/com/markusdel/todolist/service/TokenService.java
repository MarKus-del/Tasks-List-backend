package com.markusdel.todolist.service;

import com.markusdel.todolist.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private String expiration;

    public String gerarToken(Authentication authenticate) {
        User usuarioLogado = (User) authenticate.getPrincipal();
        Date dataHoje = new Date();

        Date tempoExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API do PicPay Clone")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(tempoExpiracao)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Boolean isTokenValido(String token) {
        try{
            Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims  = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
