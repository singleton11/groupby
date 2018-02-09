package com.gbconnect.groupby.services;

import com.gbconnect.groupby.domain.Login;
import com.gbconnect.groupby.domain.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
    private final UserService userService;

    @Autowired
    public JWTService(UserService userService) {
        this.userService = userService;
    }

    public Token generateToken(Login login) {
        UserDetails userDetails = userService.loadUserByUsername(login.getEmail());

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());

        return Token.builder()
                .key(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "[B@1d9af731").compact())
                .build();
    }
}
