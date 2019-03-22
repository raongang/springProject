
프로젝트 설명

1. 개발 환경
  - IDE : STS 3.9.4.RELEASE
  - DBMS : PostgreSQL 9.4
  - WAS : Tomcat 9
  - JDK : JDK 1.8
  - BootStrap : 4.3.1
  
 2. DB연결 방식
   - 보편적으로는 controller, service, serviceImpl, dao, daoImpl 등을 구현하고 mapper 를 namespace로 설정해서 XML에서 주로 쿼리를 작성한다.
   - myBatis 연결방식에서 dao데이터접근객체가 아닌 mapper를 바로 등록해서 이용할수 있다.
   - 본 프로젝트에서는 다른 방식으로 테스트하기 위해 mapper를 바로 등록해서 진행한다.

 3. 추후 보강사항 
   3-1) 검증(validation)부분 조금 더 찾아보고 보강하기
   3-2) <form:form 보강 및 내용좀 찾아보기 
   3-3) 로그인 미구현 기능 추가해야 한다
       - 로그인과 로그아웃시 세션처리 (Spring session 처리 연계해서 하기 ( @SessionAttributes) 관련 다시 보기  )
 