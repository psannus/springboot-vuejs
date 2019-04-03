package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.SecurityConstants;
import com.hrp.springapp.model.User;
import com.hrp.springapp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/registration")
    @ResponseBody
    public User registration(@RequestBody User user, HttpServletResponse response) {
        List<User> users = userService.findAll();
        boolean userExists = false;
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                userExists = true;
            }
        }
        if (!userExists) {
            String JWT = Jwts.builder()
                    .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()), SignatureAlgorithm.HS512)
                    .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                    .setIssuer(SecurityConstants.TOKEN_ISSUER)
                    .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                    .claim("rol", user.getRole())
                    .compact();

            user.setJwt(JWT);
            userService.save(user);

            Cookie jwtCookie = new Cookie("JWT", JWT);
            jwtCookie.setHttpOnly(true);
            response.addCookie(jwtCookie);
            response.setStatus(200);
            return user;
        }
        response.setStatus(400);
        return null;
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user, HttpServletResponse response) {
        User dbUser = userService.findByUsername(user.getUsername());
        if (bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {

            Cookie jwtCookie = new Cookie("JWT", dbUser.getJwt());
            jwtCookie.setHttpOnly(true);
            response.addCookie(jwtCookie);
            response.setStatus(200);

            dbUser.setJwt(null);
            dbUser.setPassword(null);
            return dbUser;
        }
        response.setStatus(400);
        return null;
    }
}
