package com.biblioteca.Biblioteca.MapperDtos;

import com.biblioteca.Biblioteca.Dtos.LoanDto;
import com.biblioteca.Biblioteca.Entities.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanDto toDto(Loan loan){
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());

        if (loan.getUser() != null){
            dto.setUserId(loan.getUser().getId());
            dto.setUserName(loan.getUser().getName());
            dto.setUserLastName(loan.getUser().getLastName());
        }

        if (loan.getLoanDetail() != null){
            dto.setLoanDetail(loan.getLoanDetail().getId());
        }

        return dto;
    }

    public Loan toEntity(LoanDto dto){
        Loan loan = new Loan();
        loan.setId(dto.getId());
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());

        return loan;
    }

}
