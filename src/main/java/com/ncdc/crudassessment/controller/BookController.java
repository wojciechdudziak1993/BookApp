package com.ncdc.crudassessment.controller;

import com.ncdc.crudassessment.entity.Book;
import com.ncdc.crudassessment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String listBooks(Model theModel) {
        log.info("Request: listBooks");
        List<Book> theBooks = bookService.findAll();
        if (theBooks.isEmpty()) {
            return "helloworld";
        }
        log.debug("Response: [{}]", theBooks);
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("book", bookService.prepareEmptyBook());
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book theBook,
                           BindingResult result) {
        log.debug("Save book [{}]", theBook);
        if (result.hasErrors()) {
            log.info("Validation failed");
            return "book-form";
        }

        bookService.save(theBook);
        return "redirect:/books/list";
    }
}
