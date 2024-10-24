package com.example.testproject.Test;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
* ----Repository ----
* mybatis 에서 Dao의 역할
* 엔티티에 대한 데이터베이스 접근을 담당
* CRUD(Create, Read, Update, Delete)와 같은 기본적인 데이터 처리 작업을 쉽게 수행할 수 있게 해줌
* Repository는 인터페이스이기 때문에 직접 객체를 생성할 수 없음
* 대신, Spring Data JPA가 자동으로 구현체를 생성해 주고, 이를 통해 데이터베이스 작업을 수행할 수 있음
*
*  extends JpaRepository<Entity, Id의 타입>
* JpaRepository를 상속받아서 JpaRepository의 다양한 기능을 사용.
*
* 이 Repository는 Test라는 객체에 대한 데이터를 다루고 그 Test 객체의 기본 키 필드의 타입이 Long이므로 <Test, Long> 이 들어옴
*
*
* 기본 CRUD 메서드들이 자동으로 생성
* save(S entity): 엔티티를 저장하거나 업데이트
* findById(ID id): 기본 키를 이용해 엔티티를 조회
* findAll(): 모든 엔티티를 조회
* deleteById(ID id): 기본 키를 이용해 엔티티를 삭제
* deleteAll() : 모든 엔티티 삭제
* existsById(ID id): 기본 키를 이용해 엔티티의 존재 여부를 확인
*
* */
public interface TestRepository extends JpaRepository<Test, Long> {

    //이름으로 조회
    List<Test> findByName(String name);
}
