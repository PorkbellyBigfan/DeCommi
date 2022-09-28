package org.zerock.decommi.service.diary;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.zerock.decommi.TestConfig;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.vo.DiaryPostList;
import org.zerock.decommi.vo.SearchCondition;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@Import(TestConfig.class)
public class DiaryServiceImplTests {
  @Autowired
  DiaryRepository repository;
  
  @Test
  public void testGetDiaryList3(SearchCondition searchCondition){
    searchCondition.setKeyword("1");
    List<DiaryPostList> result = repository.getSearch(searchCondition);
    log.info(result);
  }  
}
