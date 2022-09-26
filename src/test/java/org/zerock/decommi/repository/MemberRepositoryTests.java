package org.zerock.decommi.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;
import org.zerock.decommi.repository.member.MemberRepository;

@SpringBootTest
@WebAppConfiguration
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;
  @Autowired
  private PasswordEncoder encoder;

  @Test
  public void insertMemberDummies() {
    IntStream.rangeClosed(1, 20).forEach(i -> {
      Member m = Member.builder()
          .email("user" + i + "@decommi.com")
          .id("user" + i)
          .pw(encoder.encode("1234"))
          .fromSocial(false)
          .auth(true)
          .q1("강아지")
          .q2("고양이")
          .q3("부산")
          .build();
      m.addMemberRole(MemberRole.MEMBER);
      if (i > 9)
        m.addMemberRole(MemberRole.ADMIN);
      repository.save(m);
    });
  }

  @Test
  public void testMember() {
    Optional<Member> result = repository.findByEmail("1@1.com");
    if (result.isPresent()) {
      Member member = result.get();
      System.out.println("findByEmail : " + member);
    }
  }
}
