# DeCommi
#Develop 브랜치 공유사항

#0819- 
작성자 : 김형준
<변경사항> 

1.DecommiApplication.java 클래스 변경 
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 를 추가
사용자인증기능이 구현이 되지 않아서 더미데이터를 넣는것에 문제가 생기는것 같습니다. 그래서 해당 dependency를 disable 하는 코드를 추가했습니다.

2.몇몇 entities 주석
아직 테이블끼리의 관계가 불명확해 대부분의 테이블을 주석처리하고 기본적인 테이블만 살려뒀습니다. 조금씩 데이터를 집어넣으면서 구현하겠습니다.

# DeCommi
#Develop 브랜치 공유사항

#0830- 
작성자 : 이준호
<변경사항> 
댓글 기능에 관련된 기능을 Board와 club 폴더 등에서 참조하여
Decommi Reply로 변경 

DTO/ReplyDTO
Entity/Reply
Service/ReplyService
Controller/ReplyController

추가 및 수정
저희 파일 구조와 맞지않아서 발생하는 문제가 있으면 말씀부탁드립니다 이후 추가 수정하겠습니다

22-10-03 23:00
어드민 추가 부분

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

ㅇAdmin
-Admin 폴더 추가   (ㅇ)

ㅡAdmin/contollerㅡ
-AdminController.java 파일 추가   (ㅇ)
-ㅇ기능 추가{
- 다이어리 리스트, 삭제   (ㅇ)	
- // {"/diarymanagement" (리스트), "/diarymanagement/delete" (삭제)}
- 
- 태그 리스트, 수정, 삭제   (ㅇ)	
- // {"/tagmanagement (리스트)", "/tagmanagement/modify" (수정),"/tagmanagement/delete" (삭제)}
- 
- 회원 리스트, 수정, 삭제   (ㅇ)	
- // {"/usermanagement"(리스트) , "/usermanagement/modify" (수정) , "/usermanagement/delete" (삭제)}
- 
- 신고 리스트, 삭제   (ㅇ)	// {"/reportmanagement"(리스트) , "/reportmanagement/delete" (삭제)}
}

Admin/repositoryㅡ
- AdminMemRepository.java 파일추가			(ㅇ)
- ㅇ어드민 확인 쿼리문 추가   (ㅇ)	//Admincheck

AdminReportRepository.java 파일 추가		(ㅇ)
- ㅇ쿼리문 추가 예정   (~)
- AdminRepository.java 파일추가   (ㅇ)
- ㅇ회원 리스트 쿼리문 추가   (ㅇ)	//getDiaryByDinoAndId

Admin/dtoㅡ
- PageRequestDTO 파일 추가   (ㅇ)
- PageResultDTO 파일 추가   (ㅇ)

Admin/serviceㅡ
- AdminService.java 파일 추가   (ㅇ)
- ㅇ기능 추가{
- 회원 관리   (ㅇ)	//getUserList(리스트), UserModifier(수정), UserDeleter(삭제)
- 태그 관리   (ㅇ)	//getTags(리스트), tagModify(수정), tagDelete(삭제)
- 다이어리 관리   (ㅇ)	//getDiaryList(리스트), diaryDelete(삭제)
- 신고 관리   (ㅇ)	//getReportList(리스트), reportDelete(삭제)
- 어드민 확인   (ㅇ)	//adminChecker(체크)
}


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

-vo 파일 이름들 대문자로 수정

- TagRepository.java 수정
- @Query("select t.TagName from Tag t where diary_dino=:dino")
Optional<List<String>> OptionalfindByDiarys(Long dino);
//추가.
