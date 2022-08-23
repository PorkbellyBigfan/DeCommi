// package org.zerock.decommi.entity;

// import javax.persistence.CascadeType;
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

// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
// @JoinColumn(name = "tagId")
// private Long tagId;
// // @Column(unique = true)
// // unique로 설정하면 문제가 될 수도 있다. 다른 상위태그에 동일한 이름을 가지는 하위태그가 존재할 가능성이 있기 때문.

// private String subtagName;
// private int subtagSearchedCnt;
// private int subtagUsedCnt;
// }