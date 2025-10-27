package com.biblioteca.Biblioteca.Repository;

import com.biblioteca.Biblioteca.Entities.AuthorsNationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorNationalityRepository extends JpaRepository<AuthorsNationality, Long> {
    Optional<AuthorsNationality> findByNationality(String nationality);
}
