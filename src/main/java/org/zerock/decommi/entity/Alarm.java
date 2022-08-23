// package org.zerock.decommi.entity;

// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Entity
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @ToString(exclude = { "receiver", "sender", "bookmarkId", "scid" })
// public class Alarm extends BaseEntity {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long alarmId;
// private String alarmContent;

// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "email")
// private Member receiver;
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "email")
// private Member sender;
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "dino")
// private Diary diary;
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "bookmarkId")
// private Bookmark bookmarkId;
// // @ManyToOne(fetch = FetchType.LAZY)
// // @JoinColumn(name = "hbId")
// // private Help helpId;
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "scid")
// private ServiceCenter scid;

// }
