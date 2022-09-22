package org.zerock.decommi.entity.member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.decommi.entity.common.BaseEntity;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Tag;

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
@ToString(exclude = { "roleSet", "replyList", "likeTagList", "dislikeTagList"})
@Table(name = "d_member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;
    // 최대한 필수데이터만 저장하는게 어떨까 해서 주석처리함
    // private String name;
    // @Column(nullable = false)
    // private String mobile;
    @Column
    private String q1;
    @Column
    private String q2;
    @Column
    private String q3;

    @Column
    private boolean auth;
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> likeTagList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> dislikeTagList = new ArrayList<>();

    public void addMemberRole(MemberRole role) {
        roleSet.add(role);
    }

    public void changePw(String pw) {
        this.pw = pw;
    }

    public void changeMobile(String mobile) {
        this.pw = mobile;
    }

}
