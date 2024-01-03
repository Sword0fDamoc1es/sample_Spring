package com.example.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.sample.entity.Customer;
import com.example.sample.entity.Transaction;

@Service
public class CustomerService {
    
    // fake DB, this is public right now, but when switching to DB, it needs authentication, thus hidden.
    public Map<Customer,List<Transaction>> customerTransactionMap = new HashMap<>();
    // insert fake data
    // public CustomerService(){
    //     Customer customer1 = new Customer();
    //     customer1.setId(1L);
    //     customer1.setFirstName("John");
    //     customer1.setLastName("A");
    //     Transaction transaction1 = new Transaction(null, null, null);
    //     transaction1.setId(1L);
    //     transaction1.setAmount(120L);
    //     customerTransactionMap.put(customer1, transaction1);

    //     Customer customer2 = new Customer();
    //     customer2.setId(2L);
    //     customer2.setFirstName("Jane");
    //     customer2.setLastName("B");
    //     Transaction transaction2 = new Transaction(null, null, null);
    //     transaction2.setId(2L);
    //     transaction2.setAmount(60L);
    //     customerTransactionMap.put(customer2, transaction2);

    //     Customer customer3 = new Customer();
    //     customer3.setId(3L);
    //     customer3.setFirstName("Jack");
    //     customer3.setLastName("C");
    //     Transaction transaction3 = new Transaction(null, null, null);
    //     transaction3.setId(3L);
    //     transaction3.setAmount(30L);
    //     customerTransactionMap.put(customer3, transaction3);
    // }

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

    public Long calculateRewards(List<Transaction> transactions){
        // Long rewards = 0L;
        // for(Transaction transaction: transactions){
        //     rewards += transaction.calculateRewards();
        // }
        // return rewards;

        // using lamda expression to simplify codes.
        return transactions.stream().mapToLong(transaction -> calculateRewardsForTransaction(transaction)).sum();

    }
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
