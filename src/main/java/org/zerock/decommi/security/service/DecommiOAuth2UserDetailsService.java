package org.zerock.decommi.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.security.dto.DecommiAuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
// Open Auth를 Social로 로그인하기 위한 객체
// Object for logging in with Open Auth as Social
public class DecommiOAuth2UserDetailsService extends DefaultOAuth2UserService {

  private final MemberRepository repository;
  private final PasswordEncoder encoder;

  @Override
  // 사용자가 username, password를 이용해서 정상적 로그인하고 해당정보를 social에서 받는객체
  // An object that a user normally logs in using username and password
  // and receives the information from social
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("loadUser...... userRequest: " + userRequest);
    String clientName = userRequest.getClientRegistration().getClientName();
    log.info("clientName: " + clientName);
    log.info(userRequest.getAdditionalParameters()); // 구글로 부터 오는 정보를 확인
    OAuth2User oAuth2User = super.loadUser(userRequest);// 세션 획득
    oAuth2User.getAttributes().forEach((k, v) -> {
      log.info(k + ":" + v);
    });

    String email = null;
    if (clientName.equals("Google")) {
      email = oAuth2User.getAttribute("email");
    }
    log.info("email: " + email);
    // Member member = saveSocialMember(email);
    // return oAuth2User;
    Member member = saveSocialMember(email);
    DecommiAuthMemberDTO dto = new DecommiAuthMemberDTO(member.getEmail(),
        member.getPw(), true, member.getRoleSet().stream().map(
            role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList()),
        oAuth2User.getAttributes());
    dto.setEmail(member.getEmail());
    return dto;
  }

  private Member saveSocialMember(String email) {
    Optional<Member> result = repository.findByEmail(email, true);
    if (result.isPresent())
      return result.get();

    Member cm = Member.builder().email(email)
        .pw(encoder.encode("1")).fromSocial(true).build();
    cm.addMemberRole(MemberRole.GUEST);
    repository.save(cm);
    return cm;
  }
}
