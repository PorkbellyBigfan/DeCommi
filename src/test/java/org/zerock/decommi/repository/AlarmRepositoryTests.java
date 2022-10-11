// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Alarm;
// import org.zerock.decommi.entity.Bookmark;
// import org.zerock.decommi.entity.Diary;
// import org.zerock.decommi.entity.Heart;
// import org.zerock.decommi.entity.Help;
// import org.zerock.decommi.entity.Member;
// import org.zerock.decommi.entity.Alarm.AlarmType;

// @SpringBootTest
// public class AlarmRepositoryTests {
// @Autowired
// AlarmRepository alarmRepository;

// @Autowired
// MemberRepository memberRepository;

// @Autowired
// DiaryRepository diaryRepository;

// @Autowired
// BookmarkRepository bookmarkRepository;

// @Autowired
// HelpRepository helpRepository;

// @Test
// public void insertAlarmDummies() {
// IntStream.rangeClosed(1, 100).forEach(i -> {
// Long mno = (long) (Math.random() * 5) + 1;
// Member receiver = Member.builder().email("user" + i +
// "@decommi.com").build();
// Member sender = Member.builder().email("user" + mno +
// "@decommi.com").build();
// Diary diary = Diary.builder().dino((long) (Math.random() * 100) + 1).build();
// Bookmark bookmark = Bookmark.builder().bookmarkId((long) (Math.random() *
// 100) + 1).build();
// HelpBoard help = Help.builder().hbno((long) (Math.random() * 100) + 1).build();
// Alarm alarm = Alarm.builder()
// .receiver(receiver)
// .sender(sender)
// .diary(diary)
// .bookmarkId(bookmark)
// .helpId(help)
// .alarmContent("alarmContent" + i)
// .build();

// if (i >= 80)
// alarm.addAlarmType(AlarmType.DECLARATION);
// else if (i >= 60)
// alarm.addAlarmType(AlarmType.HELP);
// else if (i >= 40)
// alarm.addAlarmType(AlarmType.BOOKMARK);
// else
// alarm.addAlarmType(AlarmType.HEART);

// alarmRepository.save(alarm);

// });
// }
// }
