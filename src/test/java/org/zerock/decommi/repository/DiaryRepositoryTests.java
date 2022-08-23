package org.zerock.decommi.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.DiaryTag;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.Tag;

@SpringBootTest
public class DiaryRepositoryTests {
  @Autowired
  DiaryRepository repository; // Diary Repository

  @Autowired
  TagRepository tagRepository;
<<<<<<< HEAD
  // @Autowired
  // SubTagRepository subTagRepository;
=======
>>>>>>> ec97dbc3c430e2e50bb8ed28a6a8af6f67475215

  @Autowired
  DiaryTagRepository dtRepository; // Diary_Tag Repository

  @Autowired
  MemberRepository memberRepository; // Member Repository

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

      Tag tag = Tag.builder()
          .tagName("tagName" + i)
          .tagSearchedCnt(0)
          .tagUsedCnt(0)
<<<<<<< HEAD
          .parent(isNull())
=======
>>>>>>> ec97dbc3c430e2e50bb8ed28a6a8af6f67475215
          .build();
      tagRepository.save(tag);

      int count = (int) (Math.random() * 3) + 1;
      for (int j = 0; j < count; j++) {
        DiaryTag dt = DiaryTag.builder()
            .dino(diary)
            .tagName(tag)
            .build();
        dtRepository.save(dt);
      }
    });
  }
}
