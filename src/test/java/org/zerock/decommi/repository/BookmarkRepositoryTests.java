// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Bookmark;
// import org.zerock.decommi.entity.Diary;
// import org.zerock.decommi.entity.Member;

// @SpringBootTest
// public class BookmarkRepositoryTests {
// @Autowired
// BookmarkRepository bookmarkRepository;

// @Autowired
// MemberRepository memberRepository;

// @Autowired
// DiaryRepository diaryRepository;

// @Test
// public void insertBookmarkDummies() {
// IntStream.rangeClosed(1, 100).forEach(i -> {
// Long mno = (long) (Math.random() * 100) + 1;
// Long dino = (long) (Math.random() * 50) + 1;
// Member writer = Member.builder().email("user" + mno +
// "@decommi.com").build();
// memberRepository.save(writer);
// Diary diary = Diary.builder().dino(dino).build();
// diaryRepository.save(diary);

// Bookmark bookmark = Bookmark.builder()
// .bfolderName("folderName " + i)
// .isBookmark(false)
// .user(writer)
// .diary(diary)
// .build();
// bookmarkRepository.save(bookmark);
// });
// }
// }
