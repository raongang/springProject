
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

 3. 추후 추가사항 
   3-1) 로딩바 구현 
   - Ajax 로딩바 적용
   - 이미지없는 로딩바(스핀어) - loading overlay
   3-2) 삭제할때 뭔가 내가 선택한것부터 삭제가 안됨 
   - 삭제 소스 로직 분석 및 수정 
    
 