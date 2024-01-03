package com.example.sample.entity;

import lombok.Data;
import java.time.*;

// import jakarta.persistence.Entity;
// @Entity
@Data
public class Transaction {
    private Long id;
    private Long amount;
    private LocalDateTime transactionDate;

    public Transaction(Long id, Long amount, LocalDateTime transactionDate) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

}
