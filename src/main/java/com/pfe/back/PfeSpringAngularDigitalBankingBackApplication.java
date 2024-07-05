package com.pfe.back;

import com.pfe.back.dtos.CustomerDTO;
import com.pfe.back.entities.*;
import com.pfe.back.enums.AccountStatus;
import com.pfe.back.enums.OperationType;
import com.pfe.back.exceptions.BalanceNotSufficientException;
import com.pfe.back.exceptions.BankAccountNotFoundException;
import com.pfe.back.exceptions.CustomerNotFoundException;
import com.pfe.back.repository.AccountOperationRepository;
import com.pfe.back.repository.BankAccountRepository;
import com.pfe.back.repository.CustomerRepository;
import com.pfe.back.services.BankAccountService;
import com.pfe.back.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class PfeSpringAngularDigitalBankingBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeSpringAngularDigitalBankingBackApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner (BankAccountService bankAccountService){
        return args->{
            Stream.of("Hamza","Khalid","Zouhair").forEach(name ->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomer().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()* 8000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*13000,5.5,customer.getId());
                    List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
                    for (BankAccount bankAccount:bankAccounts){
                        for (int i = 0; i < 10; i++) {
                            bankAccountService.credit(bankAccount.getId(), 25000+Math.random()*13000,"Credit");
                            bankAccountService.debit(bankAccount.getId(), 1200+Math.random()*8000,"Debit");

                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });
        };
    }
    //@Bean
     CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hamza","Souhail","Hafsa").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(90000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());;
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(acc-> {
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    //accountOperation.setId(UUID.randomUUID().toString());
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }

            });

        };
    }

}
