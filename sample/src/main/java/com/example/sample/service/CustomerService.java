package com.example.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.sample.entity.Customer;
import com.example.sample.entity.Transaction;

@Service
public class CustomerService {
    
    // fake data
    private Map<Customer,Transaction> customerTransactionMap = new HashMap<>();
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
