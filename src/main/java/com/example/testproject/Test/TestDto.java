package com.example.testproject.Test;


import java.time.LocalDateTime;

/*
* ----DTO----
*
* DTO란?
* DTO는 계층간 데이터 교환을 위한 객체로, 로직을 포함하지 않고 순수한 데이터만을 가지고 있다.
* DTO는 특정 엔티티의 일부 데이터나 여러 엔티티의 조합된 데이터를 전달하는 데 사용될 수 있으며, 클라이언트와 서버 간의 통신에 최적화된 구조를 가진다.
* DTO를 사용하면 데이터 노출을 제어하고, 유연한 구조를 가지며, 성능을 최적화할 수 있다.
* 따라서 JPA와 Spring Boot 환경에서는 엔티티 대신 DTO를 사용하여 데이터를 처리하는 것이 바람직하다.
*
* record란 ? 불변 객체를 간편하게 생성할 수 있도록 도와주는 것
*
* record 를 사용하는 이유!
* 1. 불변성
* - DTO에서는 보통 데이터를 전달만 하고 수정하지 않기 때문에 불변성은 매우 유용.
* 2. 보일러플레이트 코드 감소:
* - record를 사용하면, 자동으로 생성자, equals(), hashCode(), toString() 메서드가 생성.
* - 일반적인 DTO 클래스는 이 메서드들을 일일이 정의해야 하지만, record는 이러한 보일러플레이트 코드를 자동으로 처리.
*
* */

public class TestDto {

    // 클라이언트나 외부에서 요청받은 데이터를 처리하기 위한 DTO , 사용자가 id, name, age를 보내면 이를 저장하기 위하여 사용
    public record TestRequest(String name, Integer age){}

    // 클라이언트에 응답할 데이터를 담기 위한 DTO. DB에서 조회 된 TEST 엔티티 값을 클라이언트한테 보낼때 사용
    public record TestResponse(Long id, String name, Integer age, LocalDateTime regiDate){

        // TEST 엔티티 객체로 부터 DTO 객체로 변환하는 정적 메서드
        static TestResponse fromTest(Test test){
            return new TestResponse(test.getId(),test.getName(),test.getAge(), test.getRegiDate());
        }
    }
}
