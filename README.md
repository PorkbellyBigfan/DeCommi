# DeCommi
![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4545522-13f6-4e7f-abe9-07d369d40d33/Untitled.png)

### 기획의도

코로나시국 이후 SNS의존성이 더 높아지면서, 우리의 일상생활에 SNS가 가지는 비중이 더욱 높아졌다. 하지만 그 부작용으로 개인의 권리 역시 외부의 여러 요인들로 인하여 쉽게 침해 당하기 시작했다.

또 SNS가 일상생활에 밀접하게 접촉해있다 보니 타인의 공감은 얻고 싶지만 섣불리 말하기 힘든 일들이 점점 많아졌으며, 관계를 지속하기 위한 노력도 필요하다.

그렇기에 SNS에 피로감을 느낀다고 호소하는 사람들이 많아졌다.

이러한 사회적 문제에서 착안해 기존의 SNS에서 피로감을 느낀 사람들을 목표로 개발한 폐쇄형 SNS 서비스다.

### 개발 기간

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2ede3fa3-5472-4087-ab84-8abc01239246/Untitled.png)

### 기능 요약

**폐쇄형 SNS 서비스**

- 기본적인 게시글 작성에 대한 CRUD

- 해당 게시글에 대한 좋아요, 북마크 , 신고, 댓글기능

- 태그 기능으로 특정 태그가 포함된 게시글들만 볼 수 있는 편의성을 제공합니다.

- 회원별 선호 태그리스트 기능을 추가하여 선호하는 태그들이 포함되어있는 게시글들에 접근이 용이하게 하였습니다.

### 개발 기술

| Frontend | Vue.js | SASS | Bootstrap |  |
| --- | --- | --- | --- | --- |
| Backend | SpringBoot | MariaDB |  |  |
| 배포 |  |  |  |  |
| 협업 | Discord | GitHub |  |  |

### 기술 상세 설명

> **Vue.js**
> 
> 
> 

> **Bootstrap**
> 
> 
> 

> **SpringBoot**
> 
> 
> 팀원들이 공통적으로 사용할 수 있는 **Springboot**를 사용했으며, 웹서비스 이용시 사용자 식별을 위한 토큰을 이용하기 위해 **jjwt**, 동적 검색을 위한 **querydsl** 등이 사용되었습니다.
> 

> **MariaDB**
> 
> 
> 팀원들이 공통적으로 사용 했던 MariaDB를 선택했습니다. Decommi 프로젝트는 가벼운 기능들로 이루어져 있고, MySQL과 호환이 자유로워 프로젝트에 적합하다고 판단했습니다.
> 

> **GitHub**
> 
> 
> [https://github.com/PorkbellyBigfan/DeCommi](https://github.com/PorkbellyBigfan/DeCommi)
> 

> **Discord**
> 
> 
> 의사소통을 위해 익숙한 오픈소스 채팅 프로그램인 Discord를 이용하였습니다.
> 

### 개발환경

### application.properties

| 속성명 | 속성값 | 설명 |
| --- | --- | --- |
| server.port | 8081 | Front 구동시 프록시 포트가 8081입니다. 수정시 프론트 쪽도 맞춰주셔야 합니다. |
| server.servlet.context-path | /decommi | 프런트 devserver 구동시 자동으로 decommi가 붙습니다 이 또한 수정 시 맞춰주시길 바랍니다 |
| spring.devtools.livereload.enabled | true | 속성값이 true이면 jsp, css, scss 변경시 새로고침 없이 적용이 가능합니다. |
| spring.datasource.driver-class-name | org.mariadb.jdbc.Driver | MariaDB로 세팅되어 있습니다 |
| spring.datasource.url | jdbc:mariadb://localhost:3306/decommiex | MariaDB 세팅 |
| spring.datasource.username | 접속자 이름 | MariaDB 세팅 |
| spring.datasource.password | 접속자 비밀번호 | MariaDB 세팅 |
| spring.jpa.hibernate.ddl-auto | update | JPA 하이버네이트의 설정입니다 상황에 맞게 조절해주세요. 개발환경에서는 create로 설정해주시면 테이블을 새로 설정합니다. |
| spring.jpa.properties.hibernate.format_sql | true | JPA 하이버네이트의 설정입니다. 상황에 맞게 조절해주세요 |
| spring.jpa.show-sql | true | JPA 하이버네이트의 설정입니다. 상황에 맞게 조절해주세요 |
| org.zerock.upload.path | C:\\upload | 파일 업로드 관련 설정입니다. 업로드되는 파일의 경로입니다. |
| spring.servlet.multipart.enabled | true | 파일 업로드 관련 설정입니다. 상황에 맞게 조절해 주세요 |
| spring.servlet.multipart.location | C:\\upload | 파일 업로드 관련 설정입니다. 상황에 맞게 조절해 주세요 |
| spring.servlet.multipart.max-request-size | 30MB | 파일 업로드 관련 설정입니다. 상황에 맞게 조절해 주세요 |
| spring.servlet.multipart.max-file-size | 10MB | 파일 업로드 관련 설정입니다. 상황에 맞게 조절해 주세요 |
| logging.servlet.org.springframework.security.web | trace | 스프링 부트 Security 로그 관련 설정입니다. Spring Boot의 일반적인 기본 로그 출력은 날짜 및 시간, 로그 수준, 프로세스 ID, 스레드 이름, 소스 클래스 이름 및 로그 메시지 요소를 캡처합니다. 로깅 수준은 ERROR, WARN, INFO, DEBUG 또는 TRACE 중 하나일 수 있습니다. 기본적으로 ERROR, WARN 및 INFO 수준 메시지가 기록됩니다. |
| logging.level.org.zerock | debug | Spring Boot의 일반적인 기본 로그 출력은 날짜 및 시간, 로그 수준, 프로세스 ID, 스레드 이름, 소스 클래스 이름 및 로그 메시지 요소를 캡처합니다. 로깅 수준은 ERROR, WARN, INFO, DEBUG 또는 TRACE 중 하나일 수 있습니다. 기본적으로 ERROR, WARN 및 INFO 수준 메시지가 기록됩니다. |
| server.error.whitelabel.enabled | false | 브라우져에서 오류 페이지를 보여줄 지 결정한다.
false로 지정하면 tomcat의 오류 페이지로 로딩이 된다 |
| spring.profiles.include | oauth | 설명필요! |

### 데이터베이스 구조

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d97ab9b1-79d9-49a7-9377-44c3c93d4ad5/Untitled.png)

### 멤버구성

| 이름 | 역할 | Github | 이메일 |
| --- | --- | --- | --- |
| 김형준 | 팀장,백앤드 | https://github.com/PorkbellyBigfan | porkbellyweb@gmail.com |
| 이태일 | 프론트앤드 | https://github.com/k1k2brz | refreshandreset@gmail.com |
| 박상민 | 백앤드 | https://github.com/psm418 | tkdalsdk11@gmail.com |
| 이준호 | 백앤드 | https://github.com/ZOONo-lee | zoonogi@naver.com |
