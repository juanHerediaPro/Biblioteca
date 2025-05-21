package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Dtos.LoanDto;
import com.biblioteca.Biblioteca.Entities.Loan;
import com.biblioteca.Biblioteca.Entities.User;
import com.biblioteca.Biblioteca.MapperDtos.LoanMapper;
import com.biblioteca.Biblioteca.Repository.ILoanRepository;
import com.biblioteca.Biblioteca.Repository.IUserRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.ILoanInterface;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService implements ILoanInterface {

    private final ILoanRepository iLoanRepository;
    private final LoanMapper loanMapper;
    private final IUserRepository iUserRepository;


    HashMap<String, Object> response = new HashMap<>();

    public LoanService(ILoanRepository iLoanRepository, LoanMapper loanMapper, IUserRepository iUserRepository) {
        this.iLoanRepository = iLoanRepository;
        this.loanMapper = loanMapper;
        this.iUserRepository = iUserRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createLoan(LoanDto loanDto){

        User user = iUserRepository.findById(loanDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = loanMapper.toEntity(loanDto);
        loan.setUser(user);

        Loan savedLoan = iLoanRepository.save(loan);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanMapper.toDto(savedLoan));

    }

    @Override
    public ResponseEntity<Object> getLoansByUserName(String userName){

        String normalizedName = userName.trim().toLowerCase();

        List<Loan> loans = iLoanRepository.findByUser_NameContainingIgnoreCase(normalizedName);

        if (loans.isEmpty()) {
            response.put("message", "No loans were found for the user " + userName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        List<LoanDto> loanDtos = loans.stream()
                .map(loanMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(loanDtos);
    }

    @Override
    public ResponseEntity<Object> deleteLoanById(long id){
        Optional<Loan> loan = iLoanRepository.findById(id);
        if (loan.isEmpty()){
            return new ResponseEntity<>("Loan not found", HttpStatus.NOT_FOUND);
        }
        LoanDto loanDto = loanMapper.toDto(loan.get());
        iLoanRepository.deleteById(id);
        return new ResponseEntity<>("Successfully deleted" + loanDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> updateLoan(long id, LoanDto loanDto){

        Loan loan = iLoanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id" + id));

        if (loanDto.getLoanDate() != null){
            loan.setLoanDate(loanDto.getLoanDate());
        }
        if (loanDto.getReturnDate() != null){
            loan.setReturnDate(loanDto.getReturnDate());
        }

        Loan updatedLoan = iLoanRepository.save(loan);
        return new ResponseEntity<>(loanMapper.toDto(updatedLoan), HttpStatus.OK);

    }


}
