package org.zerock.decommi.repository.member;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;

@SpringBootTest
@WebAppConfiguration
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;
  @Autowired
  private PasswordEncoder encoder;

  @Test
  // @Transactional
  public void insertMemberDummies() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member m = Member.builder()
          .email("user" + i + "@decommi.com")
          .pw(encoder.encode("1234"))
          .mobile("010" + i)
          .fromSocial(false)
          .build();
      m.addMemberRole(MemberRole.GUEST);
      if (i > 50)
        m.addMemberRole(MemberRole.MEMBER);
      if (i > 95)
        m.addMemberRole(MemberRole.ADMIN);
      repository.save(m);
    });
  }

  // @Test
  // public void testMember() {
  //   Optional<Member> result = repository.findByEmail("user9@decommi.com");
  //   if (result.isPresent()) {
  //     Member member = result.get();
  //     System.out.println("findByEmail : " + member);
  //   }
  // }
}
