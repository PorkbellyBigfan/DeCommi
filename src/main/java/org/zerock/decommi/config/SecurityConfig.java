package org.zerock.decommi.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.decommi.security.filter.ApiCheckFilter;
import org.zerock.decommi.security.filter.ApiLoginFilter;
import org.zerock.decommi.security.handler.ApiLoginFailHandler;
import org.zerock.decommi.security.handler.DecommiAccessDeniedHandler;
import org.zerock.decommi.security.handler.DecommiLoginSuccessHandler;
import org.zerock.decommi.security.handler.DecommiLogoutSuccessHandler;
import org.zerock.decommi.security.util.JWTUtil;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

  // 기본적으로 제공되는 PasswordEncoder가 아닌
  // createDelegatingPasswordEncoder 를 사용했다 추후에 유용하게 쓰인다고 한다.
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // csrf 복제방지 apiCheckFilter : 올바른 로그인확인
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
      throws Exception {
    http.csrf().disable();
    http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(apiLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public DecommiAccessDeniedHandler accessDeniedHandler() {
    return new DecommiAccessDeniedHandler();
  }

  @Bean
  public DecommiLoginSuccessHandler successHandler() {
    return new DecommiLoginSuccessHandler(passwordEncoder());
  }

  @Bean
  public DecommiLogoutSuccessHandler logoutSuccessHandler() {
    return new DecommiLogoutSuccessHandler();
  }

  @Bean
  public ApiCheckFilter apiCheckFilter() {
    return new ApiCheckFilter("/api/**/*", jwtUtil());
  }

  @Bean
  public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception {
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/member/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager);
    apiLoginFilter.setAuthenticationSuccessHandler(successHandler());
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  // apiLoginFilter 을 사용하기위해서 @bean에 등록해야 함

  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }
  @Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

}
