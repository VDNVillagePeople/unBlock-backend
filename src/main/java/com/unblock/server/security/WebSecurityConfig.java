package com.unblock.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig {

  @Configuration
  @Order(1)
  public class StaticResources extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf()
          .disable()
          .antMatcher("/static/**")
          .authorizeRequests()
          .antMatchers(HttpMethod.GET)
          .permitAll();
    }
  }

  @Configuration
  @Order(2)
  public class ApiSecurityConfigOptions extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
          .requestMatcher(new AntPathRequestMatcher("/v1/**", "OPTIONS"))
          .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS)
          .permitAll();

    }
  }

  @Configuration
  public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf()
          .disable()
          .requestMatcher(new OrRequestMatcher(
              new AntPathRequestMatcher("/v1/**", "GET"),
              new AntPathRequestMatcher("/v1/**", "POST"),
              new AntPathRequestMatcher("/v1/**", "PATCH")))
          // And filter other requests to check the presence of JWT in header
          .addFilterBefore(
              new FirebaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
  }
}
