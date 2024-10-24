package com.example.testproject.BookTest;

import com.example.testproject.Test01.BookController;
import com.example.testproject.Test01.BookDTO;
import com.example.testproject.Test01.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookControllerTest {
    @Autowired
    private BookController bookController;

    @Test
    public void postTest(){
        bookController.post(new BookDTO.BookRequest("ee","eee","소설"));

    }

    @Test

}
