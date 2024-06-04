package com.pfe.back.services;

import com.pfe.back.entities.BankAccount;
import com.pfe.back.entities.Customer;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveBankAccount(double initialBalance, String type, Long customerId);
    List<Customer> listCustomer();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String discription);
    void credit(String accountId, double amount, String discription);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
}