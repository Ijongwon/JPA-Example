package com.example.testproject.Test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    //ResponseEntity.ok(...)로 HTTP 200 OK와 함께 결과를 반환

    @GetMapping("/all")
    public ResponseEntity<List<TestDto.TestResponse>> getAll() {
        return ResponseEntity.ok(testService.getAllTest());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TestDto.TestResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<TestDto.TestResponse>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(testService.getTestByName(name));
    }

    @PostMapping("/post")
    public ResponseEntity<TestDto.TestResponse> post(@RequestBody TestDto.TestRequest request) {
        testService.saveTest(request);
        return ResponseEntity.ok().build();
    }
}
