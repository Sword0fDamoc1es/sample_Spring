package com.example.sample.customerServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sample.entity.Customer;
import com.example.sample.entity.Transaction;
import com.example.sample.resource.DateGenerator;
import com.example.sample.resource.RandomNameGenerator;
import com.example.sample.service.CustomerService;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {
    // tests for servvice functions.
    @Autowired
    private CustomerService customerService;


    public void populateTestData(CustomerService customerService){
        // we change test data here.
        // main difference is Transcation Date.
        DateGenerator dateGenerator = new DateGenerator();
        RandomNameGenerator nameGnerator = new RandomNameGenerator();
        Random random = new Random();
        List<LocalDateTime> fakeDates = dateGenerator.generateRandomDates(10);
        for(int i = 0 ; i < 10 ; i ++){
            Customer customer = new Customer();
            customer.setId((long)i);
            customer.setFirstName(nameGnerator.generateFirstName());
            customer.setLastName(nameGnerator.generateLastName());
            customer.setTransactions(new ArrayList<Transaction>());
            for(int j = 0; j <3; j++){// multi-random-transactions per customer.
                Transaction transaction = new Transaction(null, null, null);
                transaction.setId((long)i);
                transaction.setAmount(random.nextLong(0,250));
                transaction.setTransactionDate(fakeDates.get(i));
                customer.addTransaction(transaction);
            }
            customerService.customerTransactionMap.put(customer, customer.getTransactions());
        }

    }
    @BeforeAll
    public void setUpAll(){
        populateTestData(customerService);

    }

    @Test
    public void checkRewards(){
        for(Customer customer: customerService.customerTransactionMap.keySet()){
            for(Transaction transaction: customer.getTransactions()){
                assertEquals(transaction.getAmount()>100?(50+2*(transaction.getAmount()-100)):(transaction.getAmount()>50?transaction.getAmount()-50:0), customerService.calculateRewardsForTransaction(transaction));
            }
        }
    }
    @Test
    public void checkTotalRewards(){
        for(Customer customer: customerService.customerTransactionMap.keySet()){
            assertEquals(customer.getTransactions().stream().mapToLong(transaction -> customerService.calculateRewardsForTransaction(transaction)).sum(), customerService.calculateRewards(customer.getTransactions()));
        }
    }

}
