package com.example.testproject.Test;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


/*
* ----Entity-----
*
* Entity는 DB에서 영속적으로 저장된 데이터를 자바 객체로 매핑해서 '인스턴스 형태' 로 존재하는 데이터
* 테이블에 대응되는 하나의 클래스
* 자바 클래스에 @Entity를 붙히면 테이블 - 자바 클래스가 매핑됨.
*
* 주의점!
* - 접근 제어자가 public 혹은 protected 인 기본생성자가 필수!
* - final 클래스, enum, interface, inner 클래스 사용 X
* - 저장할 필드에 final 사용 X
* */


@Entity
@Getter
/*
* Setter를 쓰지 않는 이유는 entity가 변경될 수도 있을 위험이 있기때문!
* PK(고유키)는 변경되면 안되는데 setter로 데이터가 변경되면 추적이 어려움
*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
/*
* @NoArgsConstructor 는 파라미터가 없는 기본 생성자를 자동 생성해주는 어노테이션
* 이게 Entity에 붙는 이유는 JPA는 기본생성자가 없으면 오류가 생김
* (access = AccessLevel.PROTECTED) 이게 붙는 이유는 Entity의 외부 접근을 차단하기 위해 PROTECTED를 사용함
*
* @AllArgsConstructor 는 클래스의 모든 값을 파라미터로 받는 생성자를 자동 생성해주는 어노테이션
* 사용 하는 이유는 외부에 있는 빌더가 전체 필드를 가진 기본 생성자에 접근할 수 있도록 하기 위해 사용함
* */

@Table(name = "TEST")
/*
*  데이터베이스에서 사용 사용할 테이블 이름 또는 매핑시킬 테이블 명을 적는다.
*  사용하지 않는다면 클래스명을 기본으로 매핑
*  현재는 'TEST' 라는 테이블이 존재한다면 그 테이블에 매핑, 존재하지 않는다면 테이블 명을 'TEST' 로 새로 생성
*  적지 않는다면 테이블 명은 아래 클래스명과 동일한 'Test' 가 된다.
* */

public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    /*
    * @Id는 DB에 PK(고유키)를 매칭시켜주는 어노테이션
    * @GeneratedValue(strategy = GenerationType.IDENTITY) 는 기본키 생성을 DB에 위임해주는 어노테이션
    * sequence를 자동으로 생성해주는 쿼리문을 작성하는 것 처럼 id 값이 자동 생성해줌
    * @Column은 객체 필드를 테이블의 컬럼에 매칭시켜주는 어노테이션
    * (nullable = false, unique = true) 처럼 조건을 걸어 줄 수 있음
    */
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    LocalDateTime regiDate;


    /*
    * 아래 생성자는 데이터베이스에 저장할 엔티티 객체를 만들기 위해서 작성됨
    * 외부에서 요청을 받아 새로운 Test 객체를 생성하고,
    * 그 객체를 데이터베이스에 저장하는 용도로 사용
    * */
    public Test(TestDto.TestRequest request) {
        this.name = request.name();
        this.age = request.age();
        this.regiDate = LocalDateTime.now();
    }

    // 업데이트 용도로 사용
    public void update(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
