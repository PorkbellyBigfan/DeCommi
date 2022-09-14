package org.zerock.decommi.entity.member;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.zerock.decommi.entity.common.BaseEntity;

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
@ToString
@Table(name = "d_member")
public class Member extends BaseEntity {
    @Id
    private String email;
    @Column(nullable = false)
    private String pw;
    // 최대한 필수데이터만 저장하는게 어떨까 해서 주석처리함
    // private String name;
    @Column(nullable = false)
    private String mobile;
    @Column
    private boolean auth;
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

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
