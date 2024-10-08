package com.dkay229.multisql.rest.server.controller;

import com.dkay229.multisql.rest.server.entity.ClientConnection;
import com.dkay229.multisql.rest.server.entity.MultiUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private final SecretKey key = Keys.hmacShaKeyFor("your_secret_key_your_secret_key_your_secret_key_your".getBytes());

    @PostMapping("/login")
    public ClientConnection login(@RequestBody Map<String, String> loginRequest) throws AuthenticationException {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        MultiUser multiUser = new MultiUser();
        multiUser.setUsername(username);

        String jwtToken = Jwts.builder()
                .setSubject(multiUser.getUsername())
                .claim("authorities", multiUser.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(key)
                .compact();

        return new ClientConnection(multiUser,jwtToken);
    }
}

