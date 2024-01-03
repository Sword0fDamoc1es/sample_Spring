package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    
}
