package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.DiaryTag;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.Tag;

@SpringBootTest
public class DiaryRepositoryTests {
    @Autowired
    DiaryRepository repository; // Diary Repository

    @Autowired
    TagRepository tagRepository;

    @Autowired
    DiaryTagRepository dtRepository; // Diary_Tag Repository

    @Autowired
    MemberRepository memberRepository; // Member Repository

    @Test
    public void insertDiaryPosts() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            // 멤버 1~100 랜덤
            Long mno = (long) (Math.random() * 100) + 1;
            Long tagno = (long) (Math.random() * 5) + 1;
            Member writer = Member.builder().email("user" + mno +
                    "@decommi.com").build();
            Diary diary = Diary.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .openYN(false)
                    .commentYN(false)
                    .writer(writer)
                    .build();
            repository.save(diary);

            Tag tag = Tag.builder()
                    .tagName("tagName" + i)
                    .tagSearchedCnt(0)
                    .tagUsedCnt(0)
                    .isSubTag(false)
                    .tagGroup(tagno)
                    .build();
            tagRepository.save(tag);

            int count = (int) (Math.random() * 3) + 1;
            for (int j = 0; j < count; j++) {
                DiaryTag dt = DiaryTag.builder()
                        .dino(diary)
                        .tagName(tag)
                        .build();
                dtRepository.save(dt);
            }
        });
    }
}
