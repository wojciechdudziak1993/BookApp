package com.ncdc.crudassessment.rest;

import com.ncdc.crudassessment.entity.Book;
import com.ncdc.crudassessment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService theBookService) {
        bookService = theBookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        log.info("REST request: getAllBooks");
        return bookService.findAll();
    }

    @PostMapping("/books")
    public void addBook(Book book) {
        log.info("REST request: allBook");
        bookService.save(book);
    }
}