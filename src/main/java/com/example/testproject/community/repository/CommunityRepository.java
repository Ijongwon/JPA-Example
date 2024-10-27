package com.example.testproject.community.repository;

import com.example.testproject.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    // 이름으로 글 조회
    public List<Community> findByWriter(String writer);

}
