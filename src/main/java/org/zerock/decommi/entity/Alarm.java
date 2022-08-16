package org.zerock.decommi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = { "receiver", "sender", "bookmarkId", "helpId" })
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;
    // private array alarmType;
    private String alarmContent;

    // 알람 확인시간을 어떻게 저장해야할까?

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dino")
    private Diary diary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmarkId")
    private Bookmark bookmarkId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hbId")
    private Help helpId;

}
