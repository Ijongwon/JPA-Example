package com.example.testproject.community.controller;

import com.example.testproject.community.dto.CommunityDTO;
import com.example.testproject.community.entity.Community;
import com.example.testproject.community.service.CommunityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/save")
    public ResponseEntity<CommunityDTO.CommunityResponse> saveContent(@RequestBody CommunityDTO.CommunityRequest request) {
        communityService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommunityDTO.CommunityResponse>> getListAll() {
        return ResponseEntity.ok(communityService.getAll());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CommunityDTO.CommunityResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CommunityDTO.CommunityResponse>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(communityService.findByWriter(name));
    }

}
