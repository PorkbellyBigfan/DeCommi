// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.Reply;
// import org.zerock.decommi.entity.member.Member;
// import org.zerock.decommi.repository.diary.DiaryRepository;
// import org.zerock.decommi.repository.diary.ReplyRepository;
// import org.zerock.decommi.repository.member.MemberRepository;

// @SpringBootTest
// public class ReplyRepositoryTests {

// @Autowired
// ReplyRepository repository;
// @Autowired
// MemberRepository memberRepository;
// @Autowired
// DiaryRepository diaryRepository;

// @Test
// public void insertReply() {
// IntStream.rangeClosed(1, 300).forEach(i -> {
// long mno = (long) (Math.random() * 100) + 1;
// Diary diary = Diary.builder().dino(1).build();
// diaryRepository.save(diary);
// Reply reply = Reply.builder()
// .replyContent("reply..." + i)
// // .replyGroup(0)
// // .replyOrder(0)
// // .replyClass(0)
// .diary(diary)
// .writer(Member.builder().email("user" + mno + "@decommi.com").build())
// .build();
// repository.save(reply);
// });
// }
// }
