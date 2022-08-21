package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DiaryRepository diaryRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long dino = (long) (Math.random() * 300) + 1;
            long mno = (long) (Math.random() * 100) + 1;
            Diary diary = Diary.builder().dino(dino).build();
            diaryRepository.save(diary);
            Reply reply = Reply.builder()
                    .replyContent("reply..." + i)
                    .replyGroup(0)
                    .replyOrder(0)
                    .replyClass(0)
                    .diary(diary)
                    .member(Member.builder().email("user" + mno + "@decommi.com").build())
                    .build();
            repository.save(reply);

        });
    }
}
