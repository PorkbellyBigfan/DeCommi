package org.zerock.decommi.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.*;

@Getter
@Setter
@ToString
public class DecommiAuthMemberDTO extends User implements OAuth2User {
  private String email;
  private String pw;
  private String mobile;
  private boolean fromSocial;
  private boolean auth;
  private Map<String, Object> attr; // Google 에서 받은 정보 담는 곳

  public DecommiAuthMemberDTO(String username, String pw,
      boolean fromSocial, Collection<? extends GrantedAuthority> authorities,
      Map<String, Object> attr) {
    this(username, pw, fromSocial, authorities);
    this.attr = attr;
  }

  public DecommiAuthMemberDTO(
      String username, String password, boolean fromSocial,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    email = username; // ★ security와 사용자가 생성한 DB의 계정을 매칭
    this.pw = password;
    this.fromSocial = fromSocial;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attr;
  }

  // 이게 없으면 에러가 뜨는데 이유를 모르겠음
  @Override
  public String getName() {
    return null;
  }
}
