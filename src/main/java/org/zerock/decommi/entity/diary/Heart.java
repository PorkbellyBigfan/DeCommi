package org.zerock.decommi.entity.diary;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Table(name = "d_heart")
public class Heart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;
    // private boolean isHeart;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "dino")
    private Long dino;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id")
    private Long mid;

}
