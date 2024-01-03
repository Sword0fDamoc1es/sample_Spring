package com.example.sample.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.example.sample.entity.Customer;
import com.example.sample.entity.Transaction;
import com.example.sample.util.resource.DateGenerator;

@Service
public class CustomerService {
    
    // fake DB, this is public right now, but when switching to DB, it needs authentication, thus hidden.
    public Map<Customer,List<Transaction>> customerTransactionMap = new HashMap<>();
    
    // this is the function to set up fake data for testing.
    public void setUpFixedFakeData(){
        DateGenerator dateGenerator = new DateGenerator();
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setTransactions(new ArrayList<Transaction>());
        Transaction transaction1 = new Transaction(1L, 120L, dateGenerator.generateRandomDates(1).get(0));
        Transaction transaction2 = new Transaction(2L, 60L, dateGenerator.generateRandomDates(1).get(0));
        Transaction transaction3 = new Transaction(3L, 30L, dateGenerator.generateRandomDates(1).get(0));
        customer1.addTransaction(transaction1);
        customer1.addTransaction(transaction2);
        customer1.addTransaction(transaction3);
        customerTransactionMap.put(customer1, customer1.getTransactions());

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setTransactions(new ArrayList<Transaction>());
        Transaction transaction4 = new Transaction(4L, 120L, dateGenerator.generateRandomDates(1).get(0));
        Transaction transaction5 = new Transaction(5L, 60L, dateGenerator.generateRandomDates(1).get(0));
        customer2.addTransaction(transaction4);
        customer2.addTransaction(transaction5);
        customerTransactionMap.put(customer2, customer2.getTransactions());

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Jack");
        customer3.setLastName("Doe");
        customer3.setTransactions(new ArrayList<Transaction>());
        Transaction transaction6 = new Transaction(6L, 100L, dateGenerator.generateRandomDates(1).get(0));
        customer3.addTransaction(transaction6);
        customerTransactionMap.put(customer3, customer3.getTransactions());
    }


    // this is the function to get rewards of a customer.
    public Long getRewards(Long CustomerId){
        // -404 is means customer not found or no transaction of the customer.
        Long rewards = -404L;
        for(Customer customer: customerTransactionMap.keySet()){
            if(customer.getId() == CustomerId){
                rewards = calculateRewards(customer.getTransactions());
            }
        }
        return rewards;
    }

    // this function works on a list of transactions.
    // can later be used to calculate transactions in a time period but not from same customer.
    public Long calculateRewards(List<Transaction> transactions){
        // Long rewards = 0L;
        // for(Transaction transaction: transactions){
        //     rewards += transaction.calculateRewards();
        // }
        // return rewards;

        // using lamda expression to simplify codes.

        return transactions.stream().mapToLong(transaction -> calculateRewardsForTransaction(transaction)).sum();

    }

    // this function works on a single transaction.
    public Long calculateRewardsForTransaction(Transaction transaction) {
        Long amount = transaction.getAmount();
        Long rewards = 0L;
        
        if (amount > 50 && amount <= 100) {
            rewards += amount - 50;
        }
        if (amount > 100) {
            rewards += 2 * (amount - 100) + 50;
        }
        return rewards;
    }

}
