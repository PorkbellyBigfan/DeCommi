package org.zerock.decommi.repository.diary;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.QDiary;
import static org.zerock.decommi.entity.diary.QDiary.diary;
import org.zerock.decommi.entity.diary.QTag;
import org.zerock.decommi.vo.DiaryPostList;
import org.zerock.decommi.vo.SearchCondition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional(readOnly = true) 
public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements DiaryCustomRepository {
  @Autowired
  JPAQueryFactory queryFactory;
  
  public DiaryRepositoryImpl() {
    super(Diary.class);
  }


  @Override
  public List<DiaryPostList> getSearch(SearchCondition searchCondition) {
    JPQLQuery<DiaryPostList> query = queryFactory.selectFrom(diary);
        
    
    return null;
  }


}
