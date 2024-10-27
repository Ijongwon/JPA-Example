package com.example.testproject.community.dto;


import com.example.testproject.community.entity.Community;

public class CommunityDTO {

    public record CommunityRequest(String title, String writer, String content, String regDate, String updateDate){}

    public record CommunityResponse(Long indexNum, String title, String writer, String Content, String regDate, String updateDate){

        public static CommunityResponse fromCommunity(Community community){
            return new CommunityResponse(community.getIndexNum(), community.getTitle(), community.getWriter(), community.getContent(), community.getRegDate(), community.getUpdateDate());

        }
    }
}
