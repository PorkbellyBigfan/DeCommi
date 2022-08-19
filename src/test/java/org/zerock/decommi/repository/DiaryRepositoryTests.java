package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiaryRepositoryTests {
  @Autowired
  DiaryRepository repository;
  @Autowired
  MemberRepository memberRepository;

  @Test
  public void insertDiaryPosts() {
    IntStream.rangeClosed(1, 300).forEach(i -> {

    });
  }
}
