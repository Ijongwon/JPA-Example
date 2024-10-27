package com.example.testproject.communityTest;

import com.example.testproject.community.dto.CommunityDTO;
import com.example.testproject.community.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommunityServiceTest {
    @Autowired
    private CommunityService service;

    @Test
    public void test() {
        service.save(new CommunityDTO.CommunityRequest("테스트","테스","테스트 입니다.","24-10-27","24-10-27"));
    }
}
