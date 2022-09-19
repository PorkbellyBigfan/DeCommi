package org.zerock.decommi.entity.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerock.decommi.entity.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "dino")
@Table(name = "d_tag")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(unique = true, nullable = false)
    private String tagName;

    // 하위태그를 위한 컬럼
    @Column(nullable = false)
    private boolean isSubTag;
    @Column(nullable = false)
    private Long tagGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_dino", referencedColumnName = "dino")
    private Diary dino;

    public void updateDiary(Diary list) {
        this.dino = list;
    }
}
