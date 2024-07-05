package com.pfe.back.services;

import com.pfe.back.dtos.CustomerDTO;
import com.pfe.back.entities.BankAccount;
import com.pfe.back.entities.CurrentAccount;
import com.pfe.back.entities.Customer;
import com.pfe.back.entities.SavingAccount;
import com.pfe.back.exceptions.BalanceNotSufficientException;
import com.pfe.back.exceptions.BankAccountNotFoundException;
import com.pfe.back.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomer();

    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();

    CustomerDTO getCustomer(Long customerID) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerID);
}
