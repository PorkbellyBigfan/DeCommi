// package org.zerock.decommi.entity;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Entity
// @Getter
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// @ToString
// @Table(name = "d_subtag")
// public class SubTag {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long subtagId;
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "tagId")
// private Long tagId;
// @Column(unique = true)
// private String subtagName;
// private int subtagSearchedCnt;
// private int subtagUsedCnt;
// }