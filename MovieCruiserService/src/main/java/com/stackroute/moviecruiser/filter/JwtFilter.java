package com.stackroute.moviecruiser.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse response = (HttpServletResponse)res;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (HttpMethod.OPTIONS.matches(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req,res);
        }else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(7);
            final Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();

            request.setAttribute("claims",claims);
            chain.doFilter(req,res);
        }
    }
}
