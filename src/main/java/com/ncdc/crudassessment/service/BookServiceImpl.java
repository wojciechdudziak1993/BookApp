package com.ncdc.crudassessment.service;

import com.ncdc.crudassessment.dao.BookDAO;
import com.ncdc.crudassessment.entity.Author;
import com.ncdc.crudassessment.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    public static final int MAX_NUMBER_OF_AUTHORS = 5;
    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO theBookDAO) {
        bookDAO = theBookDAO;
    }


    @Override
    @Transactional
    public List<Book> findAll() {
        log.info("FindAll");
        return bookDAO.findAll();
    }

    @Override
    @Transactional
    public Book findById(int theId) {
        log.info("findById {}", theId);
        return bookDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Book book) {
        removeEmptyAuthors(book);
        log.info("save book with title {}", book.getTitle());
        bookDAO.save(book);
    }

    private void removeEmptyAuthors(Book book) {
        log.debug("Removing authors");
        book.setAuthors(getFilteredList(book.getAuthors()));
        log.debug("Authors after filtration: [{}]", book.getAuthors());
    }

    private List<Author> getFilteredList(List<Author> authors) {
        if (Objects.isNull(authors)) {
            return Collections.emptyList();
        }
        return authors.stream()
                .filter(this::isValidAuthor)
                .collect(Collectors.toList());
    }

    private boolean isValidAuthor(Author author) {
        return StringUtils.hasText(author.getFirstName()) && StringUtils.hasText(author.getLastName());
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        log.info("delete book with id: {}", theId);
        bookDAO.deleteById(theId);
    }

    @Override
    public Book prepareEmptyBook() {
        Book newBook = new Book();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_AUTHORS; i++) {
            authors.add(new Author());
        }
        newBook.setAuthors(authors);
        return newBook;
    }
}
