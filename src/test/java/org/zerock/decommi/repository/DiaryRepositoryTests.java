package org.zerock.decommi.repository;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Reply;
// import org.zerock.decommi.entity.diary.DiaryTag;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
// import org.zerock.decommi.repository.diary.DiaryTagRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.vo.DiaryPostList;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class DiaryRepositoryTests {
  @Autowired
  DiaryRepository repository; // Diary Repository
  @Autowired
  DiaryService diaryService;
  @Autowired
  TagRepository tagRepository;
  @Autowired
  ReplyRepository replyRepository;

  @Test
  public void insertDiaryDummies() {
    IntStream.rangeClosed(1, 50).forEach(i -> {
      Long mno = (long) (Math.random() * 20) + 1;
      Random randomBoolean = new Random();
      Member member = Member.builder().email("user" + mno + "@decommi.com").id("user" + i).build();
      Diary d = Diary.builder()
          .title("title" + i)
          .content("content" + i)
          .writer(member.getId())
          .openYN(randomBoolean.nextBoolean())
          .replyYN(randomBoolean.nextBoolean())
          .files(null)
          .tagList(null)
          .replyList(null)
          .build();
      repository.save(d);
    });
  }

  // 댓글 등록
  @Test
  public void insertReply() {
    Diary diary = Diary.builder().dino(1L).build();
    Member member = Member.builder().mid(4L).build();
    Reply reply = Reply.builder()
        .dino(diary)
        .member(member)
        .replyContent("replyContent")
        .replyGroup(1L)
        .replyDepth(0L)
        .replyOrder(0L)
        .build();
    replyRepository.save(reply);
  }

  @Test
  public void testGetDiaryPostByDino() {
    Diary diary = Diary.builder().dino(1L).build();
    DiaryDTO result = diaryService.getDiaryPostByDino(diary.getDino());
    log.info(result);

  }

  // 댓글 등록2
  @Test
  public void insertReplies() {
    LongStream.rangeClosed(1L, 20L).forEach(i -> {
      Diary diary = Diary.builder().dino(1L).build();
      Long rand = (long) (Math.random() * 20) + 1;
      Member member = Member.builder().mid(rand).build();
      Reply reply = Reply.builder()
          .dino(diary)
          .member(member)
          .replyContent("replyContent" + i)
          .replyGroup(i)
          .replyDepth(0L)
          .replyOrder(0L)
          .build();
      replyRepository.save(reply);

    });
  }

  // 다이어리 리스트
  @Test
  public void testGetDiaryPostList() {
    List<DiaryPostList> result = repository.getList(Sort.by("dino").descending()).get().stream().map(v -> {
      return new DiaryPostList(v);
    }).collect(Collectors.toList());
    log.info(result);
  }

  // @Test
  // @Transactional
  // public void testGetDiaryList2() {
  // Pageable pageable = PageRequest.of(0, 5, Sort.by("dino").descending());
  // Page<Diary> result = repository.getDiaryListWithTagAndReply(pageable);
  // log.info(result);
  // }
}