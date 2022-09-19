package org.zerock.decommi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
@Table(name = "d_help")
public class HelpBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hbno;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member writer;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<HelpType> roleSet = new HashSet<>();

    // @Enumerated(EnumType.ORDINAL)
    // private HelpType helpType;

    public enum HelpType {
        NOTICE, FQA
    }

    public void addHelpType(HelpType type) {
        roleSet.add(type);
    }

}