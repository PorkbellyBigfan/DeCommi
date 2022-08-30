package org.zerock.decommi.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.MemberRole;

@SpringBootTest
public class MemberRepositoryTests {
  @Autowired
  MemberRepository repository;

  @Test
  public void insertMemberDummies() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Member m = Member.builder()
          .email("user" + i + "@decommi.com")
          .pw("1234")
          .name("user" + i)
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

  @Test
  public void testMember() {
    Optional<Member> result = repository.findByEmail("user9@decommi.com");
    if (result.isPresent()) {
      Member member = result.get();
      System.out.println("findByEmail : " + member);
    }
  }
}
