package org.zerock.decommi.entity.diary;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@ToString(exclude = {"dino"})
@Getter
public class File extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;
    private String uuid;
    private String fname;
    private String fpath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_dino", referencedColumnName = "dino")
    private Diary dino;

    public void changeUuid(String uuid){
        this.uuid = uuid;
    }
    public void changeFname(String fname){
        this.fname = fname;
    }
    public void changeFpath(String fpath){
        this.fpath = fpath;
    }
}
