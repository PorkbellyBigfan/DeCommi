package org.zerock.decommi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@ToString
@Table(name = "d_member")
public class Member extends BaseEntity {
    @Id
    private String email;
    private String pw;
    private String name;
    private String mobile;
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
