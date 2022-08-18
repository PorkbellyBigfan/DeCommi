// package org.zerock.decommi.entity;

// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Entity
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// @Getter
// @ToString(exclude = "member")
// public class Help extends BaseEntity {

// // helpboard
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private int hbId;
// private String title;
// private String content;
// // private enum hbType[];
// @ManyToOne(fetch = FetchType.LAZY)
// private Member member;

// }
