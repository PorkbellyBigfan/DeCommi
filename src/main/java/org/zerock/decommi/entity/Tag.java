package org.zerock.decommi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "d_tag")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
<<<<<<< HEAD
    @Column(unique = true, nullable = false)
    private String tagName;
    private int tagSearchedCnt;
    private int tagUsedCnt;

    @ManyToOne(fetch = FetchType.LAZY) // 여러개의 하위태그가 (childTag) 하나의 상위태그 (parentTag)를 참조
    @JoinColumn(name = "parent_id") // 만약 본인이 상위 카테고리일 경우 null, 하위 카테고리일 경우 false
    @OnDelete(action = OnDeleteAction.CASCADE) // 상위 태그가 지워지면 하위태그도 자동적으로 삭제
    private Tag parent;

    public Tag(String tagName, Tag parent) {
        this.tagName = tagName;
        this.parent = parent;
    }

=======
    @Column(unique = true)
    private String tagName;
    private int tagSearchedCnt;
    private int tagUsedCnt;
>>>>>>> ec97dbc3c430e2e50bb8ed28a6a8af6f67475215
}
