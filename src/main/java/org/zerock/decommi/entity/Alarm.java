package org.zerock.decommi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@ToString(exclude = { "receiver", "sender", "bookmarkId", "diary", "helpId", "heartId" })
@Table(name = "d_alarm")
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;
    private String alarmContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_receiver")
    private Member receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_sender")
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


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AlarmType> roleSet = new HashSet<>();

    // @Enumerated(EnumType.ORDINAL)
    // private AlarmType alarmType;

    public enum AlarmType {
        DECLARATION, HELP, BOOKMARK, HEART

    }

    public void addAlarmType(AlarmType type) {
        roleSet.add(type);
    }

}