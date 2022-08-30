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
