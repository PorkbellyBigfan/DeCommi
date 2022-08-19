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
    private boolean openYN;
    private boolean commentYN;
    private int reportCnt;
    private int heartCnt;
    private int bookmarkCnt;

    @ManyToOne(fetch = FetchType.LAZY) // 한명의 멤버가 여러개의 글을 쓸수 있기때문에 @ManyToOne
    @JoinColumn(name = "email")
    private Member writer;
}
