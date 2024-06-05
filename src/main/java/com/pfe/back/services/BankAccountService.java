package com.pfe.back.services;

import com.pfe.back.entities.BankAccount;
import com.pfe.back.entities.CurrentAccount;
import com.pfe.back.entities.Customer;
import com.pfe.back.entities.SavingAccount;
import com.pfe.back.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomer();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String discription);
    void credit(String accountId, double amount, String discription);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
}
