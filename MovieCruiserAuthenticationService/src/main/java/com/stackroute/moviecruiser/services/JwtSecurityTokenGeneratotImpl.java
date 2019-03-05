package com.stackroute.moviecruiser.services;

import com.stackroute.moviecruiser.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratotImpl implements SecurityTokenGenerator {
    final static Logger log = LoggerFactory.getLogger(JwtSecurityTokenGeneratotImpl.class);
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";
        jwtToken = Jwts.builder()
                .setSubject(user.getUserId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretKey")
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User successfully logged in");

        return map;
    }

    public static void main(String[] args){
        JwtSecurityTokenGeneratotImpl j = new JwtSecurityTokenGeneratotImpl();
        User user = new User("user1","Peter","Jhon","123456",new Date());
        Map map = j.generateToken(user);
        String token = map.get("token").toString();
        log.info(token);
    }
}
