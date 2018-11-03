package com.cdm.depaul.coffeeShop.configuration;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.inMemoryAuthentication()
      .withUser("jlama").password(encoder.encode("julio123")).roles("ADMIN");

    auth.inMemoryAuthentication()
      .withUser("jtaksas").password(encoder.encode("jtaksas123")).roles("EMPLOYEE");

    auth.inMemoryAuthentication()
      .withUser("gvla").password(encoder.encode("gvla123")).roles("EMPLOYEE");
  }

  /**
   * Configures security of the web app paths.
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()

      /* Tell Spring to a user to see the static resources too.
      * If this is not specified everything breaks... */
      .antMatchers("/webjars/**").permitAll()
      .antMatchers("/resources/**").permitAll()

      /* Allow any request to access this path */
      .antMatchers("/registration").permitAll()

      /* Any request must be authenticated can see the rest of the views. */
      .anyRequest().authenticated()
      .and()

      /* To use your own login view and not the built-in */
      .formLogin().loginPage("/login")

      /* Tells Spring to redirect to this view after the credentials where verified. */
      .defaultSuccessUrl("/home")

      /* This '/authenticateteUser' will be called by Spring and then use the Spring Security Filters.
        No coding required for this path. */
      .loginProcessingUrl("/authenticateUser")

      /* Let anyone see the login page. */
      .permitAll()

      /* For the logout feature. Allow them to access it. */
      .and()
      .logout()
      .permitAll();
  }
}
