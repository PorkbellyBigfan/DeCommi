package org.zerock.decommi.entity.diary;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.decommi.entity.common.BaseEntity;
import org.zerock.decommi.entity.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "tagList", "files", "replyList" })
@Table(name = "d_diary")
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dino;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean openYN; // 공개여부
    @Column(nullable = false)
    private boolean replyYN; // 댓글 허용여부
    @Column(nullable = false)
    private String writer;

    @Builder.Default
    @OneToMany(mappedBy = "dino", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> tagList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "dino", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "dino", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    // public void changeTitle(String title) {this.title = title;}
    // public void changeContent(String content) { this.content = content; }
}
