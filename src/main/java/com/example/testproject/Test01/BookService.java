package com.example.testproject.Test01;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(BookDTO.BookRequest request){
        Book book = new Book(request);

        bookRepository.save(book);
    }

    public List<BookDTO.BookResponse> getBookByGenre(String genre){
        return bookRepository.findByGenre(genre)
                .stream()
                .map(BookDTO.BookResponse::fromBook)
                .toList();
    }
}
