package com.hrp.springapp.jwt;

import com.hrp.springapp.model.User;
import com.hrp.springapp.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter {

    private final UserService userService;

    public JwtAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public boolean validateToken(String jwt, Long id) {
        User user = userService.findById(id);
        return (user != null && jwt.equals(user.getJwt()));
    }

    //@Override
    //protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    //                                        FilterChain filterChain, Authentication authentication) {
    //    var user = ((SecurityProperties.User) authentication.getPrincipal());
    //
    //    var roles = user.getAuthorities()
    //            .stream()
    //            .map(GrantedAuthority::getAuthority)
    //            .collect(Collectors.toList());
    //
    //    var signingKey = SecurityConstants.JWT_SECRET.getBytes();
    //
    //    response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    //}
}
