package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.DiaryTag;
import org.zerock.decommi.entity.Member;

@SpringBootTest
public class DiaryRepositoryTests {
  @Autowired
  DiaryRepository repository; // 다이어리 리파지토리
  @Autowired
  MemberRepository memberRepository; // 멤버리파지토리
  @Autowired
  DiaryTagRepository dtRepository;

  @Test
  public void insertDiaryPosts() {
    IntStream.rangeClosed(1, 300).forEach(i -> {
      // 멤버 1~100 랜덤
      Long mno = (long) (Math.random() * 100) + 1;
      Member writer = Member.builder().email("user" + mno + "@decommi.com").build();
      Diary diary = Diary.builder()
          .title("title" + i)
          .content("content" + i)
          .openYN(false)
          .commentYN(false)
          .reportCnt(0)
          .heartCnt(0)
          .bookmarkCnt(0)
          .writer(writer)
          .build();
      repository.save(diary);

      int count = (int) (Math.random() * 3) + 1;
      for (int j = 0; j < count; j++) {
        DiaryTag dt = DiaryTag.builder().build();
      }
    });
  }
}
