package com.temzu.cafefresh.configs;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.temzu.cafefresh.filters.JwtAuthenticationFilter;
import com.temzu.cafefresh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(toH2Console())
            .disable()
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/orders/**")).authenticated()
            .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("ADMIN", "MANAGER")
            .anyRequest().permitAll()
        )
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(withDefaults())
        .authenticationProvider(authenticationProvider()).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService.userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

}
