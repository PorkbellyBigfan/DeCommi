// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Help;
// import org.zerock.decommi.entity.Member;
// import org.zerock.decommi.entity.Help.HelpType;

// @SpringBootTest
// public class HelpRepositoryTests {
// @Autowired
// HelpRepository helpRepository;
// @Autowired
// MemberRepository memberRepository;

// @Test
// public void insertSCDummies() {
// IntStream.rangeClosed(1, 100).forEach(i -> {
// Member writer = Member.builder().email("user" + i + "@decommi.com").build();
// Help help = Help.builder()
// .user(writer)
// .title("title" + i)
// .content("content" + i)
// .build();
// if (i < 50)
// help.addHelpType(HelpType.NOTICE);
// else if (i >= 50)
// help.addHelpType(HelpType.FQA);
// helpRepository.save(help);
// });
// }
// }
