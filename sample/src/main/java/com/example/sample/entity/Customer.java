package com.example.sample.entity;

import java.util.List;

// import jakarta.persistence.Entity;
import lombok.Data;
// @Entity
@Data
public class Customer {
    // avoid overflow
    private Long id;
    private String firstName;
    private String lastName;
    // each Customer has a list of transactions
    private List<Transaction> transactions;
    // currently not in use. Explain in README.md 
    private Long rewards;
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}