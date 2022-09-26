package org.zerock.decommi.repository.diary;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.vo.SearchCondition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryCustomRepository {
  @Autowired
  JPAQueryFactory factory;

  @Override
  public List<Diary> search(SearchCondition searchCondition) {
    QDiary qDiary = QDiary.diary;
    // https://goodteacher.tistory.com/396
    BooleanBuilder builder = new BooleanBuilder();

    return null;
  }

  private BooleanExpression containTitle(String title){
    if(title ==null || title.isEmpty()){
      return null;
    }
    return Diary.getTitle().containsIgnoreCase(title);
  }

}
