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
import org.zerock.decommi.entity.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = { "dino", "member" })
@Table(name = "d_reply")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @Column(nullable = false)
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary dino;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 대댓글 구현을 위한 속성들
    @Column(nullable = false)
    private Long replyGroup;
    @Column(nullable = false)
    private Long replyDepth;
    @Column(nullable = false)
    private Long replyOrder;


    public void changeReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}