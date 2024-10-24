package com.example.testproject.Test;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TestService {
    private final TestRepository testRepository;


    /*
    * 모든 항목 조회
    * 데이터베이스에서 모든 Test 엔티티를 조회한 후, TestDto.TestResponse로 변환하여 리스트로 반환
    * findAll()은 JpaRepository에서 제공하는 기본 메서드로, 모든 데이터를 조회
    * Stream API를 이용해, Test 엔티티 리스트를 TestResponse DTO 리스트로 변환
    * map(TestDto.TestResponse::fromTest)는 Test 엔티티를 TestResponse로 변환하는 메서드를 호출
    *
    * */
    public List<TestDto.TestResponse> getAllTest() {
        return testRepository.findAll()
                .stream()
                .map(TestDto.TestResponse::fromTest)
                .toList();
    }

    //id로 조회 - 주어진 ID로 Test 엔티티를 조회하여, 존재하면 TestDto.TestResponse DTO로 변환하여 반환
    public TestDto.TestResponse getTestById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()) {
            return TestDto.TestResponse.fromTest(test.get());
        }
        return null;
    }

    //이름으로 조회 - findByName이라는 쿼리를 만들어서 사용
    public List<TestDto.TestResponse> getTestByName(String name) {
        return testRepository.findByName(name)
                .stream()
                .map(TestDto.TestResponse::fromTest)
                .toList();
    }

    /*
     *  TestDto.TestRequest DTO를 받아 저장하거나 업데이트합니다.
     *  request.id()로 조회하여 해당 ID로 저장된 엔티티가 존재하는지 확인.
     *  없을 경우: 새로운 Test 엔티티를 생성하여 저장.
     *  있을 경우: 기존 엔티티를 가져와 해당 엔티티의 필드를 업데이트 (name, age 필드)
     *  변경된 객체를 저장: testRepository.save(test)를 통해 신규 혹은 업데이트된 엔티티를 저장
     *  트랜잭션 처리: @Transactional이 적용되어 모든 작업이 하나의 트랜잭션 내에서 처리됩니다. 중간에 예외가 발생하면 작업이 롤백
     */
//    @Transactional
//    public void saveTest(TestDto.TestRequest request){
//        Optional<Test> opt = testRepository.findById(request.id());
//        Test test;
//        if(opt.isEmpty()){
//            test = new Test(request);
//        } else {
//            test = opt.get();
//            test.update(request.name(), request.age());
//        }
//        testRepository.save(test);
//    }

    @Transactional
    public void saveTest(TestDto.TestRequest request) {
        // 새로운 Test 객체 생성
        Test test = new Test(request);

        // 엔티티를 저장
        testRepository.save(test);
    }

}
