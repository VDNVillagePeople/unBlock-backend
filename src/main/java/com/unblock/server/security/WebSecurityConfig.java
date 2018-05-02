package com.unblock.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /*http.csrf().disable();
    http.headers().frameOptions().disable();*/
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll()
        .antMatchers(HttpMethod.GET, "/h2/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/static/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/v1/login")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/v1/newUser")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {}

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
