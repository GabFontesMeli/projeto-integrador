package com.example.projetointegrador.configs.security;

import com.example.projetointegrador.model.UserU;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGenerator {

  @Value("${jwt.secret}")
  private String secret;

//   @Value("${app.jwttoken.message}")
  private String message = "Token gerado com sucesso";

  public Map<String, String> generateToken(UserU user) {
    String jwtToken="";
    jwtToken = Jwts.builder().setSubject(user.getName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
    Map<String, String> jwtTokenGen = new HashMap<>();
    jwtTokenGen.put("token", jwtToken);
    jwtTokenGen.put("message", message);
    return jwtTokenGen;
  }
}