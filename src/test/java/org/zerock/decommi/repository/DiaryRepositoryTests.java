package org.zerock.decommi.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class DiaryRepositoryTests {
  @Autowired
  DiaryRepository repository; // Diary Repository

  @Autowired
  TagRepository tagRepository;

  @Autowired
  MemberRepository memberRepository; // Member Repository

  @Test
  public void test() {
    log.info(repository.findByDino(1L));
  }

  // @Test
  // public void insertDiaryPosts() {
  // IntStream.rangeClosed(1, 10).forEach(i -> {
  // // 멤버 1~100 랜덤
  // Long mno = (long) (Math.random() * 100) + 1;
  // Long tagno = (long) (Math.random() * 5) + 1;
  // Member writer = Member.builder().email("user" + mno +
  // "@decommi.com").build();
  // Diary diary = Diary.builder()
  // .title("title" + i)
  // .content("content" + i)
  // .openYN(false)
  // .replyYN(false)
  // .writer(writer)
  // .build();
  // repository.save(diary);

  // Tag tag = Tag.builder()
  // .tagId(tagno)
  // .tagName("tagName" + tagno)
  // // .tagSearchedCnt(0)
  // // .tagUsedCnt(0)
  // .isSubTag(false)
  // .tagGroup(tagno)
  // .build();
  // tagRepository.save(tag);

  // int count = (int) (Math.random() * 3) + 1;
  // for (int j = 0; j < count; j++) {
  // DiaryTag dt = DiaryTag.builder()
  // .dino(diary)
  // .tagId(tag)
  // .build();
  // dtRepository.save(dt);
  // }
  // });
  // }

  // @Test
  // public void testGetListPage() {
  // Pageable pageable = PageRequest.of(0, 10, Sort.by("dino").descending());
  // Page<Object[]> result = repository.getListPage(pageable);
  // result.get().forEach(row -> {
  // Object[] arr = (Object[]) row;
  // System.out.println(Arrays.toString(arr));
  // });
  // }
}
