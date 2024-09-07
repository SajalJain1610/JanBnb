package com.main.janBnb.config;

import com.main.janBnb.entity.User;
import com.main.janBnb.repository.UserRepository;
import com.main.janBnb.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestMapper extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserRepository userRepository;

    public JwtRequestMapper(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String tokenHeader = request.getHeader("Authorization");
         if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
              String token = tokenHeader.substring(8, tokenHeader.length() - 1);
              String username = jwtService.getUsername(token);
              User user = userRepository.findByUsername(username).get();
              String userRole = user.getRole().toString();
             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                     user,
                     null,
                     Collections.singleton(new SimpleGrantedAuthority(userRole)));
              authToken.setDetails(new WebAuthenticationDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authToken);
         }
         filterChain.doFilter(request,response);
    }
}
