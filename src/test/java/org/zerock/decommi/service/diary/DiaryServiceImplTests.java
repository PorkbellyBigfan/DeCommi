package org.zerock.decommi.service.diary;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.vo.DiaryPostList;
import org.zerock.decommi.vo.SearchCondition;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
// @Import(TestConfig.class)
public class DiaryServiceImplTests {
  @Autowired
  DiaryRepository repository;


}
