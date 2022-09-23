package org.zerock.decommi.repository;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
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

  @Test
  public void insertDiaryDummies(){
    IntStream.rangeClosed(1,50).forEach(i->{
      Random randomBoolean = new Random();
      Member member = Member.builder().email(i+"@"+i+".com").build();
      Diary d = Diary.builder()
        .title("title"+i)
        .content("content"+i)
        .writer(member.getEmail())
        .openYN(randomBoolean.nextBoolean())
        .replyYN(randomBoolean.nextBoolean())
        .build();
    });
  }


    
  }