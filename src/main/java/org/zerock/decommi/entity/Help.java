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
@ToString(exclude = "user")
@Table(name = "d_help")
public class Help extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hbId;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member user;

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