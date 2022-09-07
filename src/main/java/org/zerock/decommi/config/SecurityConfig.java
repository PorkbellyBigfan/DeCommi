package org.zerock.decommi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.decommi.security.filter.ApiCheckFilter;
import org.zerock.decommi.security.filter.ApiLoginFilter;
import org.zerock.decommi.security.handler.ApiLoginFailHandler;
import org.zerock.decommi.security.handler.DecommiAccessDeniedHandler;
import org.zerock.decommi.security.handler.DecommiLoginSuccessHandler;
import org.zerock.decommi.security.handler.DecommiLogoutSuccessHandler;
import org.zerock.decommi.security.service.DecommiUserDetailsService;
import org.zerock.decommi.security.util.JWTUtil;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DecommiUserDetailsService userDetailsService;

  // 기본적으로 제공되는 PasswordEncoder가 아닌
  // createDelegatingPasswordEncoder 를 사용했다 추후에 유용하게 쓰인다고 한다.
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // 로그인에 대한 설정
        .formLogin().loginPage("/member/login").loginProcessingUrl("/login").successHandler(successHandler())

        .and() // 로그아웃에 대한 설정
        .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler())

        .and() // 권한 오류에 대한 설정
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler())

        .and()// csrf 관련 설정 비활성화
        .csrf().disable()

        .authorizeHttpRequests()
        .antMatchers("/**").permitAll(); // 임시로 모든 url 접근 허용
    // .antMatchers("/sample/all").hasRole("USER")
    // .antMatchers("/sample/member").hasRole("MANAGER")
    // .antMatchers("/sample/admin").hasRole("ADMIN")
    // .antMatchers("/member/modify").authenticated();

    // http://localhost:8080/club/login/oauth2/code/google
    // http.oauth2Login().successHandler(successHandler());//by handler
    // http.oauth2Login().defaultSuccessUrl("/"); //forwarding

    http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(userDetailsService);

    http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
    // apiLoginFilter를 활용할 때 기본 security를 적용하겠다.
    // Basic security will be applied when using apiLoginFilter.
    http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
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
  public ApiLoginFilter apiLoginFilter() throws Exception {
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/members/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager());
    apiLoginFilter.setAuthenticationSuccessHandler(successHandler());
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }
}
