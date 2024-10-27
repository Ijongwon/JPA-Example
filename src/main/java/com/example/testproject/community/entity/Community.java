package com.example.testproject.community.entity;

import com.example.testproject.community.dto.CommunityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indexNum;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private String regDate;

    @Column
    private String updateDate;

    public Community(CommunityDTO.CommunityRequest request) {
        this.title = request.title();
        this.writer = request.writer();
        this.content = request.content();
        this.regDate = request.regDate();
        this.updateDate = request.updateDate();
    }

}
