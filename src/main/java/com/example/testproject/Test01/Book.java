package com.example.testproject.Test01;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "BOOK")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long bookNum;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String genre;

    public Book(BookDTO.BookRequest request){
        this.title = request.title();
        this.writer = request.writer();
        this.genre = request.genre();
    }


}
