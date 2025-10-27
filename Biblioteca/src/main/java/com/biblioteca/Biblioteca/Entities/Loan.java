package com.biblioteca.Biblioteca.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoanDetail loanDetail;

    public Loan() {
    }

    public Loan(Long id, LocalDate loanDate, LocalDate returnDate, User user, LoanDetail loanDetail) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.user = user;
        this.loanDetail = loanDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoanDetail getLoanDetail() {
        return loanDetail;
    }

    public void setLoanDetail(LoanDetail loanDetail) {
        this.loanDetail = loanDetail;
    }
}
