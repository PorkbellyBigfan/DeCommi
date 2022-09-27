// package org.zerock.decommi.repository.diary;

// import java.util.List;

// import javax.persistence.EntityManager;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;
// import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.QDiary;
// import org.zerock.decommi.vo.SearchCondition;

// import com.querydsl.core.BooleanBuilder;
// import com.querydsl.core.types.dsl.BooleanExpression;
// import com.querydsl.jpa.impl.JPAQueryFactory;

// import lombok.RequiredArgsConstructor;

// @Repository
// @Transactional(readOnly = true) 
// public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements DiaryCustomRepository {
//   @Autowired
//   JPAQueryFactory factory;
  
//   public DiaryRepositoryImpl() {
//     super(Diary.class);
//   }

//   @Override
//   public List<Diary> search(Pageable pageable, SearchCondition searchCondition) {
//     QDiary qDiary = QDiary.diary;
//     // https://goodteacher.tistory.com/396
//     private BooleanBuilder builder = new BooleanBuilder();

//     return null;
//   }


// }
