package org.zerock.decommi.entity.diary;

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
@ToString(exclude = { "dino", "tagId" })
@Table(name = "d_diary_tag")
public class DiaryTag extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dtNo;
  // Mapping Table
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dino")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Diary dino;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tagId")
  private Tag tagId;

}
