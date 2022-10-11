package org.zerock.decommi.entity.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString
@Table(name = "d_liketaglist")
public class LikeTagList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long lid; // likeTagId
  private String tagName;

  // @ManyToOne(fetch = FetchType.LAZY)
  private String email;
}
