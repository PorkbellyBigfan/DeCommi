// package org.zerock.decommi.repository.diary;

// import java.util.List;

// import javax.persistence.EntityManager;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import
// org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;
// import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.QDiary;
// import org.zerock.decommi.entity.diary.QTag;
// import org.zerock.decommi.entity.member.QMember;
// import org.zerock.decommi.vo.SearchCondition;

// import com.querydsl.core.BooleanBuilder;
// import com.querydsl.core.types.dsl.BooleanExpression;
// import com.querydsl.jpa.JPQLQuery;
// import com.querydsl.jpa.impl.JPAQuery;
// import com.querydsl.jpa.impl.JPAQueryFactory;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Transactional(readOnly = true)
// @Log4j2
// public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements
// DiaryCustomRepository {

// public DiaryRepositoryImpl() {
// super(Diary.class);
// }

// QDiary qDiary = QDiary.diary;
// QMember qMember = QMember.member;
// QTag qTag = QTag.tag;
// }
