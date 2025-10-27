package com.biblioteca.Biblioteca.Repository;

import com.biblioteca.Biblioteca.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByName(String name);
    boolean existsByName(String name);
}
