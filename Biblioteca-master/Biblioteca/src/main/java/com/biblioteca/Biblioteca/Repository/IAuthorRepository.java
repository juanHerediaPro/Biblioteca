package com.biblioteca.Biblioteca.Repository;

import com.biblioteca.Biblioteca.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByLastName(String lastName);
}
