package org.zerock.decommi.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StreamUtils;
import org.zerock.decommi.dto.ApiSessionDTO;
import org.zerock.decommi.security.dto.DecommiAuthMemberDTO;
import org.zerock.decommi.security.util.JWTUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

//체크 이후 로그인 필터 생성
@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

  private JWTUtil jwtUtil;

  public ApiLoginFilter(String defaultFilterProcessUrl, JWTUtil jwtUtil) {
    super(defaultFilterProcessUrl);
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    log.info("ApiLoginFilter........ attemptAuthentication");
    log.info("request.getRequestURI():" + request.getRequestURI());

    ServletInputStream inputStream = request.getInputStream();
    String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("msgBody:: " + msgBody);
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = new JSONObject();
    log.info("jsonObject"+jsonObject);
    try {
      jsonObject = (JSONObject) parser.parse(msgBody);
      log.info("jsonObject"+jsonObject);
    } catch (Exception e) {
      e.printStackTrace();
    }

    String email = jsonObject.get("email").toString();
    String pw = jsonObject.get("pw").toString();
    log.info("id: " + email + "/pw: " + pw);
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);

    return getAuthenticationManager().authenticate(authToken);
  }

  // 토큰 생성
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    log.info("successfulAuthentication... authResult:" + authResult.getPrincipal());
    // Jason Web Token publishing
    // 인증이 되었으니까 세션을 통해서 email을 획득
    Long mid = ((DecommiAuthMemberDTO) authResult.getPrincipal()).getMid();
    String email = ((DecommiAuthMemberDTO) authResult.getPrincipal()).getEmail();
    String token = null; // 초기화
    ObjectMapper mapper = new ObjectMapper(); // 맵퍼에 넣음
    String curl = ""; // 클라이언트 주소
    try {
      token = "Bearer " + jwtUtil.generateToken(mid, email);
      ApiSessionDTO sessionDTO = DecommiAuthToSessionDTO((DecommiAuthMemberDTO) authResult.getPrincipal(), token, curl);
      // sessionDTO에 토큰과 클라이언트 주소를 넣음

      String res = mapper.writeValueAsString(sessionDTO);
      response.setContentType("application/json;charset=utf-8");
      // response.setCharacterEncoding("UTF-8");
      response.getOutputStream().write(res.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private ApiSessionDTO DecommiAuthToSessionDTO(DecommiAuthMemberDTO dto,
      String token, String curl) {
    ApiSessionDTO sessionDTO = ApiSessionDTO.builder()
        .mid(dto.getMid())
        .email(dto.getEmail())
        .id(dto.getId())
        .curl(curl)
        .fromSocial(dto.isFromSocial())
        .attr(dto.getAttr())
        .token(token)
        .build();
    return sessionDTO;
  }
}
