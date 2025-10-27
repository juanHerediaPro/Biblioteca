package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Dtos.LoanDto;
import org.springframework.http.ResponseEntity;

public interface ILoanInterface {
    public ResponseEntity<Object> createLoan(LoanDto loanDto);
    public ResponseEntity<Object> getLoansByUserName(String userName);
    public ResponseEntity<Object> deleteLoanById(long id);
    public ResponseEntity<Object> updateLoan(long id, LoanDto loanDto);
}
