package org.zerock.decommi.repository;

// import static org.mockito.ArgumentMatchers.anyList;

// import java.util.List;
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
// import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
// import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.Help;
// import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.Heart;
// import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.BookmarkRepository;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.HeartRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
import org.zerock.decommi.repository.diary.ReportRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.diary.BookmarkService;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.diary.HeartServiceImpl;
import org.zerock.decommi.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@RequiredArgsConstructor
public class Repository_ServiceTests {
    @Autowired
    HeartServiceImpl himpl;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DiaryService diaryService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    HeartRepository heartRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    BookmarkService bookmarkService;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    HelpRepository helpRepository;

    // MemberTests
    @Test
    public void findById() {
        Optional<Member> result = memberRepository.findById("di1");
        log.info(result);
    }

    @Test
    public void findByIdWithSocial() {
        Optional<Member> result = memberRepository.findByIdWithSocial("di1", false);
        log.info(result);
    }

    @Test
    public void findByEmailWithSocial() {
        Optional<Member> result = memberRepository.findByEmailWithSocid("user1@decommi.com", false);
        log.info(result);
    }

    @Test
    public void findByEmail() {
        String email = "user1@decommi.com";
        Optional<Member> result = memberRepository.findByEmail(email);
        log.info(result);
        System.out.println("=========================");
        if (result.isPresent()) {
            Member member = result.get();
            System.out.println(member);
        }
    }

    @Test
    public void getPageList() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("mid").descending());
        Page<Member> result = memberRepository.getPageList(pageable);
        for (Member member : result) {
            System.out.println(member);
        }
    }

    @Test
    public void getMemberDTO() {
        MemberDTO dto = new MemberDTO();
        log.info(dto);
        // log.info(memberService.getMemberDTO("user1@decomii.com")); //dto ????????? pk???
        // ???????
    }

    @Test
    public void emailCheck() {
        String email = "userdfgfdggfd111@decommi.com";
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            System.out.println("?????? ???????????? ?????????");
        } else {
            System.out.println("?????? ????????? ?????????");
        }
    }

    // DiaryTests

    @Test
    @Transactional
    public void getByDino() {
        log.info(diaryRepository.getByDino(1L));
    }

    @Test
    @Transactional
    public void findByDino() {
        log.info(diaryRepository.findByDino(1L));
    }

    // @Test
    // public void getDiaryListWithTag(){
    // Pageable pageable = PageRequest.of(0, 10, Sort.by(""));
    // Page<Diary> result = diaryRepository.getDiaryListWithTag(pageable);
    // for (Diary diary : result) {
    // System.out.println(diary);
    // }
    // }
    // @Test
    // public void registerDiary(){

    // }

    @Test
    public void getDiaryByDinoAndId() {
        log.info(diaryRepository.getDiaryByDinoAndId(1L, "di1"));
    }

    // @Test
    // public void deleteDiary(){
    // DiaryDTO dto =
    // DiaryDTO.builder().dino(2L).writer("user2@decommi.com").build();
    // diaryService.deleteDiary(dto.getDino());
    // }
    // HeartTests
    @Test
    public void test() {
        log.info(himpl.getListDino(1L));
    }

    // @Test
    // public void insertHeart(){
    // Heart heart = Heart.builder().heartId(1L).dino(1L).mid(1L).build();
    // heartRepository.save(heart);
    // }

    // BookmarkTests
    // ReportTests
    @Test
    public void findByReid() {
        // log.info(reportRepository.findByReid(1L));
        log.info(reportRepository.checkReportLogByMemberIdAndDiaryId(1L, 1L));
    }

    @Test
    public void insertHelp() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder().mid(20L).build();
            Help help = Help.builder().content("FAQ" + i).title("FAQ" + i).writer(member).helpType("FAQ")
                    .build();
            helpRepository.save(help);
        });
    }

    @Test
    public void insertHelp2() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder().mid(20L).build();
            Help help = Help.builder().content("NOTICE" + i).title("NOTICE" + i).writer(member).helpType("NOTICE")
                    .build();
            helpRepository.save(help);
        });
    }
}
