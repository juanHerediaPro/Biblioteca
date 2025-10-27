package com.biblioteca.Biblioteca.Repository;

import com.biblioteca.Biblioteca.Entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByUser_NameContainingIgnoreCase(String name);
}
