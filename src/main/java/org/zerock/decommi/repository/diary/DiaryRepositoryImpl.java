package org.zerock.decommi.repository.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.decommi.entity.diary.Diary;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements DiaryRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    public DiaryRepositoryImpl() {
        super(Diary.class);
    }

}
