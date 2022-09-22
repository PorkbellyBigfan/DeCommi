package org.zerock.decommi.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

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
import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.DiaryTag;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;
import org.zerock.decommi.repository.diary.DiaryRepository;
// import org.zerock.decommi.repository.diary.DiaryTagRepository;
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

  // @Autowired
  // DiaryTagRepository dtRepository; // Diary_Tag Repository

  @Autowired
  MemberRepository memberRepository; // Member Repository

  @Autowired
  private PasswordEncoder encoder;

  
  @Test
  public void insertDiaryPosts() {
    IntStream.rangeClosed(1, 20).forEach(i -> {
      Long mno = (long) (Math.random() * 20) + 1;
      // 멤버 1~100 랜덤
      //  Member writer = Member.builder().email("user" + i + "decommi.com").build();
      Member writer = Member.builder().email("user" + i +
          "@decommi.com").pw(encoder.encode("1234"))
          .id("id" + i).q1("q1").q2("q2").q3("q3").fromSocial(false).auth(true).build();
          memberRepository.save(writer);
      writer.addMemberRole(MemberRole.MEMBER);
      if (i > 17)
        writer.addMemberRole(MemberRole.ADMIN);
      Diary diary = Diary.builder()
          .title("title" + i)
          .content("content" + i)
          .openYN(false)
          .replyYN(true)
          .writer("user"+i+"@decommi.com")
          .build();
      repository.save(diary);
    });
  }
      @Test
      public void insertTag(){
        IntStream.rangeClosed(1, 20).forEach(i->{
        Long tagname = (long) (Math.random() * 5) + 1;
        Long tagG = (long) (Math.random() * 5) + 1;

      Tag tag = Tag.builder()
          .tagName("tagName" + i)
          .isSubTag(false)
          .tagGroup(tagG)
          .dino(Diary.builder().dino((long) i).build())
          .build();
      tagRepository.save(tag);
    });
    }

    
    @Test
    public void test() {
      log.info(repository.findByDino(1L));
    }

    
    @Test
    public void findemail(){
        Optional<Member> result = memberRepository.findByEmail("user1@decommi.com");
        log.info(result);
    }


    
  }