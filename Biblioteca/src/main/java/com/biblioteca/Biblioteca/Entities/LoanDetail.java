package com.biblioteca.Biblioteca.Entities;

import com.biblioteca.Biblioteca.Enums.LoanStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "LoanDetails")
public class LoanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_id", nullable = false, unique = true)
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    public LoanDetail() {
    }

    public LoanDetail(Long id, Loan loan, Book book, LoanStatus status) {
        this.id = id;
        this.loan = loan;
        this.book = book;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
