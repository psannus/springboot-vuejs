package com.hrp.springapp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public HashMap<String, String> checkAuthentication(HttpServletRequest request) {
        HashMap<String, String> auth = new HashMap<>();

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("JWT")) auth.put("JWT", cookie.getValue());
            if (cookie.getName().equals("userId")) auth.put("userId", cookie.getValue());
        }
        System.out.println();
        if (auth.getOrDefault("JWT", null) != null
                && auth.getOrDefault("userId", null) != null
                && jwtAuthenticationFilter.validateToken(auth.get("JWT"), Long.parseLong(auth.get("userId")))) {
            return auth;
        }
        return null;
    }
}
