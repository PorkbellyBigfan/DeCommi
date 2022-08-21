package org.zerock.decommi.entity;

import javax.persistence.Entity;
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
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "diary", "tag" })
@Table(name = "d_diary_tag")
public class DiaryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diarytagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dino")
    private Diary diary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagId")
    private Tag tag;
}
