package com.pfe.back.web;

import com.pfe.back.entities.Customer;
import com.pfe.back.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/cusomers")
@Slf4j
@AllArgsConstructor
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<Customer> customers(){
        return bankAccountService.listCustomer();
    }

}
