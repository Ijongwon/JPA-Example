package com.example.testproject.Test01;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RequiredArgsConstructor
@RestController

public class BookController {

    private final BookService bookService;

    @PostMapping("/post")
    public ResponseEntity<Book> post(@RequestBody BookDTO.BookRequest request){
        bookService.saveBook(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO.BookResponse>> getByGenre(String genre){
        return ResponseEntity.ok(bookService.getBookByGenre(genre));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDTO.BookResponse>> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

}
