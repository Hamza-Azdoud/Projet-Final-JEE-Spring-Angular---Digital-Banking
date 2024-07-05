package com.pfe.back.web;

import com.pfe.back.dtos.CustomerDTO;
import com.pfe.back.entities.Customer;
import com.pfe.back.exceptions.CustomerNotFoundException;
import com.pfe.back.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cusomers")
@Slf4j
@AllArgsConstructor
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomer();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerID) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerID);
    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerID}")
    public CustomerDTO updateCustomer (@PathVariable Long customerID ,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerID);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }
}
