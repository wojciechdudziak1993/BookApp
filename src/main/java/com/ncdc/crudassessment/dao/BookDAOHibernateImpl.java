package com.ncdc.crudassessment.dao;

import com.ncdc.crudassessment.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class BookDAOHibernateImpl implements BookDAO {

    private EntityManager entityManager;

    @Autowired
    public BookDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Book> findAll() {
        log.info("findAll");
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Book> theQuery = currentSession.createQuery("from Book", Book.class);
        return theQuery.getResultList();
    }

    @Override
    public Book findById(int theId) {
        log.info("findById {}", theId);
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Book.class, theId);
    }

    @Override
    public void save(Book book) {
        log.info("save book with title {}", book.getTitle());
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(book);
    }

    @Override
    public void deleteById(int theId) {
        log.info("delete book with id: {}", theId);
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);
        theQuery.executeUpdate();
    }
}