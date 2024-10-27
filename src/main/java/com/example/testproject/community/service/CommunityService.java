package com.example.testproject.community.service;


import com.example.testproject.community.dto.CommunityDTO;
import com.example.testproject.community.entity.Community;
import com.example.testproject.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public void save(CommunityDTO.CommunityRequest request){
        Community community = new Community(request);

        communityRepository.save(community);
    }

    public List<CommunityDTO.CommunityResponse> getAll(){
        return communityRepository.findAll()
                .stream()
                .map(CommunityDTO.CommunityResponse::fromCommunity)
                .toList();


    }

    public List<CommunityDTO.CommunityResponse> findByWriter(String writer){
        return communityRepository.findByWriter(writer)
                .stream()
                .map(CommunityDTO.CommunityResponse::fromCommunity)
                .toList();
    }

    public CommunityDTO.CommunityResponse findById(Long id){
        Optional<Community> community = communityRepository.findById(id);
        return CommunityDTO.CommunityResponse.fromCommunity(community.get());
    }

}
