/* package com.ANieto.api.Auth;

import com.ANieto.api.services.JwtTokenServices;
import com.ANieto.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;


 @Component

public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private UsuarioServices myUserDetailService;

    @Autowired
    private JwtTokenServices jwtService;
     @Bean
     public static BCryptPasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }
    @Override
    @Bean
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request,response);
            return;
        }
        final String token = header.substring(7);
        final String username = jwtService.ValidateTokenAndGetUsername(token);
        if(username == null){
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
       // SecurityContextHolder.getContext().getAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
*/