package com.finger.Godfinger.repository;

import com.finger.Godfinger.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book>findByIsbn(String isbn);
}
