package com.example.sample.entity;

import java.util.List;

// import jakarta.persistence.Entity;
import lombok.Data;
// @Entity
@Data
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Transaction> transactions;
    private Long rewards;
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}