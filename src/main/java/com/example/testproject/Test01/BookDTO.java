package com.example.testproject.Test01;

public class BookDTO {

    public record BookRequest(String title, String writer, String genre){}

    public record BookResponse(Long BookNum, String title, String writer, String genre) {

        static BookResponse fromBook(Book book) {
            return new BookResponse(book.getBookNum(), book.getTitle(), book.getWriter(), book.getGenre());
        }
    }

}
