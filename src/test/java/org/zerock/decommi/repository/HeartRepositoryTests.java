// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Diary;
// import org.zerock.decommi.entity.Heart;
// import org.zerock.decommi.entity.Member;

// @SpringBootTest
// public class HeartRepositoryTests {
// @Autowired
// HeartRepository heartRepository;
// @Autowired
// MemberRepository memberRepository;
// @Autowired
// DiaryRepository diaryRepository;

// @Test
// public void insertHeartDummies() {
// IntStream.rangeClosed(1, 100).forEach(i -> {
// Long mno = (long) (Math.random() * 80) + 1;
// Long dino = (long) (Math.random() * 100) + 1;
// Member writer = Member.builder().email("user" + mno +
// "@decommi.com").build();
// Diary diary = Diary.builder().dino(dino).build();
// Heart heart = Heart.builder()
// .isHeart(false)
// .writer(writer)
// .post(diary)
// .build();
// heartRepository.save(heart);
// });
// }
// }
