package com.ncdc.crudassessment.dao;

import com.ncdc.crudassessment.entity.Book;

import java.util.List;

public interface BookDAO {

    public List<Book> findAll();

    public Book findById(int theId);

    public void save(Book book);

    public void deleteById(int theId);
}
