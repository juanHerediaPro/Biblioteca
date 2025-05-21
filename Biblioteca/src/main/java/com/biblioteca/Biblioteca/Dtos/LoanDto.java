package com.biblioteca.Biblioteca.Dtos;

import java.time.LocalDate;

public class LoanDto {

    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Long userId;
    private String userName;
    private String userLastName;
    private Long loanDetail;

    public LoanDto() {
    }

    public LoanDto(Long id, LocalDate loanDate, LocalDate returnDate, Long userId, String userName, String userLastName, Long loanDetail) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getLoanDetail() {
        return loanDetail;
    }

    public void setLoanDetail(Long loanDetail) {
        this.loanDetail = loanDetail;
    }
}
