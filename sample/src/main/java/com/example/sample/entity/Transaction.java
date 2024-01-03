package com.example.sample.entity;

import lombok.Data;
import java.time.*;
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

    // public Long calculateRewards() {
    //     Long rewards = 0L;
    //     if (amount > 50 && amount <= 100) {
    //         rewards += amount - 50;
    //     }
    //     if (amount > 100) {
    //         rewards += 2 * (amount - 100) + 50;
    //     }
    //     return rewards;
    // }
}
