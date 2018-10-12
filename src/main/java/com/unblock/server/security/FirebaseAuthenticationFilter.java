package com.unblock.server.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FirebaseAuthenticationFilter extends GenericFilterBean {
  static final String HEADER_STRING = "Authorization";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String idToken = ((HttpServletRequest) request).getHeader(HEADER_STRING);

    if (idToken == null || idToken.trim().isEmpty()) {
      throw new SecurityException("Must provide an authentication token.");
    }
    try {
      System.out.println("Doing authentication");
      System.out.println(idToken);
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
      String uid = decodedToken.getUid();
      System.out.println(uid);

      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(uid, decodedToken));
      filterChain.doFilter(request, response);
    } catch (FirebaseAuthException e) {
      System.out.println(e);
      throw new SecurityException(e);
    }
  }
}
