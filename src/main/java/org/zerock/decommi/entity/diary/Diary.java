package org.zerock.decommi.entity.diary;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerock.decommi.entity.common.BaseEntity;
import org.zerock.decommi.entity.member.Member;

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
@ToString(exclude = "writer")
@Table(name = "d_diary")
public class Diary extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long dino;
    private String title;
    private String content;
    private boolean openYN; // 공개여부
    private boolean commentYN; // 댓글 허용여부

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 한명의 멤버가 여러개의 글을 쓸수 있기때문에 @ManyToOne
    @JoinColumn(name = "email")
    private Member writer;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
    // public void changeOpenOption(boolean OpenYN){this.openYN}
    // public void changeCommentOption(boolean commentYN){};

}
