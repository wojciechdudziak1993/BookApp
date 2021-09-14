package com.ncdc.crudassessment.service;

import com.ncdc.crudassessment.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll();

    public Book findById(int theId);

    public void save(Book book);

    public void deleteById(int theId);

    public Book prepareEmptyBook();
}
