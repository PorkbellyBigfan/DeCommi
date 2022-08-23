package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Alarm;
import org.zerock.decommi.entity.Member;

@SpringBootTest
public class AlarmRepositoryTests {
    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    MemberRepository memberRepository;

    // @Autowired
    // DiaryRepository diaryRepository;

    // @Autowired
    // BookmarkRepository bookmarkRepository;

    // @Autowired
    // ServiceCenterRepository serviceCenterRepository;
    @Test
    public void insertAlarmDummies(){
        IntStream.rangeClosed(10, 15).forEach(i->{
            Long dino = (long) (Math.random() * 50) + 1;
            Member receiver = Member.builder().email("user"+i+"@decommi.com").build();
            Member sender = Member.builder().email("user"+dino+"@decommi.com").build();
            System.out.println(receiver);
            Alarm alarm = Alarm.builder()
            .receiver(receiver)
            .sender(sender)
            .alarmContent("alarmContent"+i)
            .build();
            alarmRepository.save(alarm);

        });
    }
}
