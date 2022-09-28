package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.QDiary;
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
    List<DiaryPostList> query = queryFactory
      .select(Projections.constructor(DiaryPostList.class, QDiary.diary))
      .from(QDiary.diary)
      .where(
        // tagListContain(searchCondition),
        QDiary.diary.title.contains(searchCondition.getKeyword()).or(QDiary.diary.content.contains(searchCondition.getKeyword()))
      )
      .orderBy(QDiary.diary.dino.desc())
      .fetch();
      return query;

  }
  // private BooleanExpression tagListContain(SearchCondition searchCondition){
  //   List<String>tagList = searchCondition.getTagList();
  //   boolean tagOption = searchCondition.isTagOption();

  //   // 태그 필터에 태그가 등록되지 않으면 null 을 반환하고, 
  //   // 태그 필터에 태그가 등록되었을 경우,
  //   // QDiary 도메인의 tagList와 비교하고 사용자의 요구에 따라 아래 두 옵션중 하나를 선택해서 게시글 반환한다. 
  //   // 1. tagOption = true ::: searchCondition.getTagList()의 값들이 QDiary의 tagList에 들어있는 태그와 모두 일치하는 게시글 반환
  //   // 2. tagOption = false ::: searchCondition.getTagList()의 값들이 QDiary의 tagList에 들어있는 태그 중 하나라도 일치하는 게시글 반환
  //   return tagList.size()==0 ? null : QDiary.diary.tagList.contains(searchCondition.getTagList().stream().map().fetch();
  // }
}
