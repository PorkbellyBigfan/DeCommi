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
    // private int replyCnt; //댓글 카운트
    // private int reportCnt; // 신고 카운트
    // private int heartCnt; // 하트 카운트
    // private int bookmarkCnt; // 북마크 카운트

    @ManyToOne(fetch = FetchType.LAZY) // 한명의 멤버가 여러개의 글을 쓸수 있기때문에 @ManyToOne
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
