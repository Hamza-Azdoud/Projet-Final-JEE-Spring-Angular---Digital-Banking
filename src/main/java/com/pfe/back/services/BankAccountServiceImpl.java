package com.pfe.back.services;

import com.pfe.back.entities.BankAccount;
import com.pfe.back.entities.CurrentAccount;
import com.pfe.back.entities.Customer;
import com.pfe.back.entities.SavingAccount;
import com.pfe.back.exceptions.CustomerNotFoundException;
import com.pfe.back.repository.AccountOperationRepository;
import com.pfe.back.repository.BankAccountRepository;
import com.pfe.back.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private AccountOperationRepository accountOperationRepository;
    Logger log = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saveng new Custmer");
        Customer saveCustomer = customerRepository.save(customer);
        return saveCustomer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {

        Customer customer= customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw  new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);

        CurrentAccount saveBankAccount = bankAccountRepository.save(currentAccount);
        return saveBankAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw  new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);

        SavingAccount saveBankAccount = bankAccountRepository.save(savingAccount);
        return saveBankAccount;
    }

    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String discription) {

    }

    @Override
    public void credit(String accountId, double amount, String discription) {

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {

    }
}
