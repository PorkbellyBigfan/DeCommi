package org.zerock.decommi.repository;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
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
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.LikeTagListRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.diary.MyDiaryService;
import org.zerock.decommi.service.member.LikeTagListService;

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
  @Autowired
  MyDiaryService mdService;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  LikeTagListRepository likeTagListRepository;
  @Autowired
  LikeTagListService likeTagListService;



  @Test
  public void insertDiaryDummies() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Long mno = (long) (Math.random() * 20) + 1;
      Random randomBoolean = new Random();
      Member member = Member.builder().email("user" + mno +
          "@decommi.com").id("user" + i).build();
      Diary d = Diary.builder()
          .title("test" + i)
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
    Member member = Member.builder().mid(3L).build();
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
    ArrayList<String> tagList = new ArrayList<String>();
    tagList.add("test");
    tagList.add("1");
    PageRequestDTO requestDTO = PageRequestDTO.builder()
        .page(1).size(10).type("t")
        .tagList(tagList).sort("dino").keyword("test").build();
    PageResultDTO<DiaryDTO, Diary> resultDTO = diaryService.getDiaryPostList(requestDTO);
    log.info("Search Result :::: " + resultDTO);
  }

  // 마이 다이어리 리스트
  @Test
  public void testGetMyDiaryPostList() {
    ArrayList<String> arrList = new ArrayList<String>();
    arrList.add("test");
    arrList.add("1");
    PageRequestDTO requestDTO = PageRequestDTO.builder()
        .page(1).size(10).type("t")
        .tagList(arrList).sort("dino").writer("2@2.2").keyword("테스트").build();
    PageResultDTO<DiaryDTO, Diary> resultDTO = mdService.getMyDiaryPostList(requestDTO);
    log.info("Search Result :::: " + resultDTO);
  }

  // 모든 태그리스트 출력
  @Test
  public void getAllTagList() {
    log.info(tagRepository.getAllTagName());
  }

  // @Test
  // public void getAllLikeTagListByMid() {
  //   Member member = Member.builder().mid(3L).build();
  //   log.info(likeTagListRepository.getAllLikeTagListByMid(member));

  // }

  // 댓글
  @Test
  public void fghgfdhgfhgfhgfhgh() {
    // log.info(replyRepository.getLastestReplyGroupWhereMatchWithDino(1L));
    Member member = Member.builder().mid(1L).build();
    Diary diary = Diary.builder().dino(1L).build();
    // log.info(replyRepository.findByMember(member));
    // log.info(replyRepository.getReplyByDinoAndMid(diary,member));
    // log.info(replyRepository.getReplyListByDino(1L));
    log.info(replyRepository.getLastestReplyGroupWhereMatchWithDino(1L));
  }

  @Test
  public void deleteReply() {
    Optional<Reply> checkReply = replyRepository.getReplyByRnoAndMid(3L, 11L);
    if (checkReply.isPresent()) {
      System.out.println("삭제 성공공");
    } else {
      System.out.println("없다 댓글");
    }
  }

  @Test
  public void modifyReply() {
    Optional<Reply> checkReply = replyRepository.getReplyByRnoAndMid(1L, 1L);
    if (checkReply.isPresent()) {
      Reply reply = checkReply.get();
      reply.changeReplyContent("수정정정gdffd");
      replyRepository.save(reply);
    }
  }

  @Test
  void commentPagingTest() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Reply> rPage = replyRepository.getPageList(pageable, 1L);
    for (Reply reply : rPage) {
      log.info(reply);
    }
  }

  @Test
  void registReply() {
    Member member = Member.builder().mid(5L).build();
    Diary diary = Diary.builder().dino(1L).build();
    Optional<Reply> checkMember = replyRepository.getReplyByDinoAndMid(diary,
        member);
    if (!checkMember.isPresent()) {
      log.info(checkMember);
      Optional<List<Long>> lastestrg = replyRepository.getLastestReplyGroupWhereMatchWithDino(diary.getDino());
      Long setrg = 1L;
      if (lastestrg.get().size() != 0) {
        setrg = lastestrg.get().get(0) + 1;
      }
      log.info(setrg);
      Reply reply = Reply.builder().replyContent("sfdkjfk")
          .replyGroup(setrg)
          .replyDepth(0L)
          .replyOrder(0L)
          .build();
      log.info(reply);
      replyRepository.save(reply);

    } else {
      // return checkMember.get().getRno();
    }

  }

  @Test
  void addNewReply() {
    Member member = Member.builder().mid(6L).build();
    Diary diary = Diary.builder().dino(1L).build();
    Reply reply = Reply.builder().dino(diary).member(member).replyGroup(1L).replyDepth(1L)
        .replyContent("대댓글").replyOrder(0L).build();
    replyRepository.save(reply);
  }

  @Test
  void replyPagingTest() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Reply> rPage = replyRepository.getPageList(pageable, 1L);
    for (Reply reply : rPage) {
      log.info(reply);
    }
  }

  @Test
  void getReplyListByDino() {
    Pageable pageable = PageRequest.of(0, 1);
    Page<Reply> rPage = replyRepository.getPageList(pageable, 1L);
    if (!rPage.isEmpty()) {
      log.info(rPage);
    }
  }

  @Test
  void testEditLikeTagList(){
    
  }
  @Test
  void testGetLikeTagList(){
    log.info(likeTagListService.getLikeTagList(1L));
  }


}