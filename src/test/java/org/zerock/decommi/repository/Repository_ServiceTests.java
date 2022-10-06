package org.zerock.decommi.repository;

import static org.mockito.ArgumentMatchers.anyList;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.Help2DTO;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.entity.QHelp;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Heart;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.member.Bookmark;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.BookmarkRepository;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.HeartRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
import org.zerock.decommi.repository.diary.ReportRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.HelpService;
import org.zerock.decommi.service.diary.BookmarkService;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.diary.HeartServiceImpl;
import org.zerock.decommi.service.member.MemberService;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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
    @Autowired
    HelpService helpService;

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
        // log.info(memberService.getMemberDTO("user1@decomii.com")); //dto 타입을 pk로변경?
    }

    @Test
    public void emailCheck() {
        String email = "userdfgfdggfd111@decommi.com";
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            System.out.println("이미 존재하는 이메일");
        } else {
            System.out.println("사용 가능한 이메일");
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
    // public void deleteDiary() {
    // DiaryDTO dto =
    // DiaryDTO.builder().dino(2L).writer("user2@decommi.com").build();
    // diaryService.deleteDiary(dto.getDino());
    // }

    // HeartTests
    @Test
    public void test() {
        log.info(himpl.getListDino(1L));
    }

    @Test
    public void insertHeart() {
        Heart heart = Heart.builder().hid(1L).dino(1L).mid(1L).build();
        heartRepository.save(heart);
    }

    // BookmarkTests
    @Test
    public void getFolderList() {
        // List<Bookmark> result = bookmarkRepository.getFolderList(2L);
        // for (Bookmark bookmark : result) {
        // log.info(bookmark);
        // }
        Member member = Member.builder().mid(14L).build();
        List<Bookmark> result = bookmarkRepository.getFolderList(member.getMid());
        for (Bookmark bookmarks : result) {
            log.info(bookmarks);
        }
    }

    @Test
    public void getListDino() {
        log.info(bookmarkRepository.getBookmarList(2L, "folderName6"));
    }

    // @Test
    // public void getListDino2() {
    // List<M>
    // HashMap<String, Object> result = bookmarkService.getListDino(2L,
    // "folderName6");
    // if (((Optional<Member>) result).isPresent()) {

    // }
    // }

    // ReportTests
    @Test
    public void findByReid() {
        // log.info(reportRepository.findByReid(1L));
        log.info(reportRepository.checkReportLogByMemberIdAndDiaryId(1L, 1L));
    }

    // Help
    @Test
    public void insertHelp() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder().mid(20L).build();
            Help help = Help.builder().content("content" + i).title("title" + i).writer(member)
                    .build();
            helpRepository.save(help);
        });
    }

    @Test
    public void Noticeregister() {
        HelpDTO dto = HelpDTO.builder().title("Notice").content("배개배배배백엔드드드")
                .writer(20L).build();
        helpService.Noticeregister(dto);
    }

    @Test
    public void QnAregister() {
        HelpDTO dto = HelpDTO.builder().title("QnA").content("배개배배배백엔드드드")
                .writer(20L).build();
        helpService.QnAregister(dto);
    }

    @Transactional
    @Test
    public void testRead() {
        Optional<Help> result = helpRepository.findById(23L);
        log.info(result);
    }

    @Test
    public void deleteByIdhhhh() {
        Optional<Help> checkHelp = helpRepository.getHelpByMid(23L, 20L);
        if(checkHelp.isPresent()){
            helpRepository.delete(checkHelp.get());

        }
    }

    @Test
    public void modifyHelp() {
        Optional<Help> checkHelp = helpRepository.getHelpByMid(2L, 21L);
        if (checkHelp.isPresent()) {
            Help help = checkHelp.get();
            help.changContent("수정수정gggggg");
            help.changTitle("수정정?");
            helpRepository.save(help);
        }
    }

    @Test
    public void deleteHelp() {
        Optional<Help> checkHelp = helpRepository.getHelpByMid(1L, 20L);
        if (checkHelp.isPresent()) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }
    }

    @Transactional
    @Test
    public void getNoticeList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(100).build();
        PageResultDTO<HelpDTO, Help> resultDTO = helpService.getNoticeList(pageRequestDTO);
        for (HelpDTO helpDTO : resultDTO.getDtoList()) {
            System.out.println("=================" + helpDTO);
        }
    }

    // @Test
    // public void getFQAList() {
    //     PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(150).build();
    //     PageResultDTO<HelpDTO, Help> resultDTO = helpService.getQnAList(pageRequestDTO);
    //     for (HelpDTO helpDTO : resultDTO.getDtoList()) {
    //         System.out.println("=================" + helpDTO);
    //     }
    // }

    @Test
    public void querydslHelp() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by("hbno").descending());

        // 동적인 쿼리를 하기위해 Q도메인필요
        QHelp qHelp = QHelp.help; // 1
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();// 2 질의하기 위한 객체(BooleanBuilder)
        BooleanExpression expression = qHelp.title.contains(keyword);// 3 질의 식 ,,BooleanExpression-조건을 담기 위한 객체
        builder.and(expression); // 4 객체가 and로 질의 식을 실행
        Page<Help> result = helpRepository.findAll(builder, pageable);// 5
        result.stream().forEach(help -> {
            System.out.println(help);
        });
    }

    @Test
    public void insertBookmarkDummies() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Long mno = (long) (Math.random() * 20) + 1;
            Long dino = (long) (Math.random() * 10) + 1;
            Member writer = Member.builder().mid(mno).build();
            Diary diary = Diary.builder().dino(dino).build();

            Bookmark bookmark = Bookmark.builder()
                    .bfolderName("folderName " + i)
                    .mid(writer.getMid())
                    .dino(diary.getDino())
                    .build();
            bookmarkRepository.save(bookmark);
        });
    }
}
