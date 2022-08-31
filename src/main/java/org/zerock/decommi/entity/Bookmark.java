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
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "user", "diary" })
@Table(name = "d_bookmark")
public class Bookmark extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookmarkId;
  private String bfolderName;
  private boolean isBookmark;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "email")
  private Member user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dino")
  private Diary diary;
}