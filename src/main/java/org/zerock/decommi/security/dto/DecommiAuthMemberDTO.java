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
  private Long mid;
  private String id;
  private String email;
  private String pw;
  private boolean fromSocial;
  private boolean auth;
  private Map<String, Object> attr; // Google 에서 받은 정보 담는 곳

  // 만약의 구글로그인 기능 용도
  public DecommiAuthMemberDTO(String username, String pw, boolean fromSocial,
      Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
    this(username, pw, fromSocial, authorities);
    this.attr = attr;
  }

  // mid 안들어가는 경우 (보류)
  public DecommiAuthMemberDTO(
      String username, String password, boolean auth,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    email = username; // ★ security와 사용자가 생성한 DB의 계정을 매칭
    this.pw = password;
    this.auth = auth;
  }

  // 실제로 사용하는 Auth DTO
  public DecommiAuthMemberDTO(
      String username, String password, String id, Long mid, boolean auth,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.mid = mid;
    this.id = id;
    email = username; // ★ security와 사용자가 생성한 DB의 계정을 매칭
    this.pw = password;
    this.auth = auth;
  }

  // 구글 계정 용도
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
