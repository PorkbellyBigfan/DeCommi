package org.zerock.decommi.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.security.dto.DecommiAuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
// 로그인 처리해주는 클래스
public class DecommiUserDetailsService implements UserDetailsService {

  private final MemberRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("DecommiUserDetailsService loadUserByUsername: " + username);
    Optional<Member> result = repository.findByEmail(username);
    if (!result.isPresent()) {
      throw new UsernameNotFoundException("Check email or Social");
    }
    Member member = result.get();
    log.info("Decommi Member: " + member);

    // DecommiAuthMemberDTO는 User를 상속받았기 때문.
    DecommiAuthMemberDTO dto = new DecommiAuthMemberDTO(
        member.getEmail(), member.getPw(), member.getId(),
        member.getMid(), member.isAuth(),
        member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
            .collect(Collectors.toList()));
    dto.setEmail(member.getEmail());
    dto.setId(member.getId());
    dto.setAuth(member.isAuth());
    return dto;
  }
}
