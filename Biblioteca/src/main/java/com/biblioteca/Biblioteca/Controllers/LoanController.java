package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Dtos.LoanDto;
import com.biblioteca.Biblioteca.ServiceInterfaces.ILoanInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final ILoanInterface iLoanInterface;

    public LoanController(ILoanInterface iLoanInterface) {
        this.iLoanInterface = iLoanInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createLoan(@RequestBody LoanDto loanDto){
        return iLoanInterface.createLoan(loanDto);
    }
    @GetMapping("/get")
    public ResponseEntity<Object> getLoansByUserName(@RequestParam String userName){
        return iLoanInterface.getLoansByUserName(userName);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteLoanById(@RequestParam long id){
        return iLoanInterface.deleteLoanById(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateLoad(@RequestParam long id, @RequestBody LoanDto loanDto){
        return iLoanInterface.updateLoan(id,loanDto);
    }
}
