JPA란 ?
ORM (Object Relational Mapping) 객체 관계 매핑 기술의 표준으로 인터페이스의 모음이다.
따라서 실제로 동작하는 것이 아니기 때문에 구현체가 필요한데,
JPA 표준을 구현한 구현체는 아래와 같이 Hibernate, EclipseLink, DataNucleus가 있으며
대표적으로 Hibernate를 사용한다.

ORM이란?
객체와 관계형 데이터 베이스를 매핑해 주는 기술
객체는 객체대로 설계 할 수 있고, 관계형 데이터베이스는 관계형 데이터베이스대로 설계가 가능하도록
ORM 프레임워크가 중간에서 매핑을 해줌.

JPA의 동작 과정
JPA는 Java 애플리케이션과 JDBC 사이에서 동작하는데
Java 애플리케이션에서 JPA를 사용하면 내부에서 JDBC API를 사용하여 SQL를 DB에 전달하고 결과를 반환받는다.


Backend build가 안될때!
- JDK 버전 확인
- port 사용중인지 확인

Front build 방법 (node.js 설치는 필수)
cd src/main/front 로 이동
npm install
npm run-script build 순서대로 입력
전부 완료되면
npm start 실행


