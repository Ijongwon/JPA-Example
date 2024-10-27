package com.example.testproject.communityTest;

import com.example.testproject.community.controller.CommunityController;
import com.example.testproject.community.dto.CommunityDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommunityControllerTest {
    @Autowired
    private CommunityController controller;

    @Test
    public void test() {
        controller.saveContent(new CommunityDTO.CommunityRequest("테스트2","테스2","테스트 입니다2.","24-10-27","24-10-27"));
    }

    @Test
    public void test2() {
        log.info(controller.getListAll().toString());
    }
}
