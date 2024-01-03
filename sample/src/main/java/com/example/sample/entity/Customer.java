package com.example.sample.entity;

import java.util.List;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Transaction> transactions;
    private Long rewards;
}