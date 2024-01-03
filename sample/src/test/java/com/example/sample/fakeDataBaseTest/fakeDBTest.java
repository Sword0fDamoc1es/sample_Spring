package com.example.sample.fakeDataBaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sample.entity.Customer;
import com.example.sample.entity.Transaction;
import com.example.sample.service.CustomerService;

@SpringBootTest
public class fakeDBTest {
    @Autowired
    private CustomerService customerService;

    public void populateTestData(CustomerService customerService){
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("A");
        Transaction transaction1 = new Transaction(null, null, null);
        transaction1.setId(1L);
        transaction1.setAmount(120L);
        ArrayList<Transaction> firstListTransaction = new ArrayList<Transaction>();
        firstListTransaction.add(transaction1);
        customerService.customerTransactionMap.put(customer1, firstListTransaction);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("B");
        Transaction transaction2 = new Transaction(null, null, null);
        transaction2.setId(2L);
        transaction2.setAmount(60L);
        ArrayList<Transaction> secondListTransaction = new ArrayList<Transaction>();
        secondListTransaction.add(transaction2);
        customerService.customerTransactionMap.put(customer2, secondListTransaction);

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Jack");
        customer3.setLastName("C");
        Transaction transaction3 = new Transaction(null, null, null);
        transaction3.setId(3L);
        transaction3.setAmount(30L);
        ArrayList<Transaction> thirdListTransaction = new ArrayList<Transaction>();
        thirdListTransaction.add(transaction3);
        customerService.customerTransactionMap.put(customer3, thirdListTransaction);
    }
    @BeforeEach
    public void setUp(){
        //customerService = new CustomerService();
        populateTestData(customerService);
    }

    // 0. since we use lombok, we dont need to check entity or pojo itself.
    // if further, facing data type converting problem, lombok may be the reason of it. so do that check here.


    // 1. fake DB initalize check. Check if data is inserted correctly.
    @Test
    public void checkCustomer(){ // by checking if exists.
        for(Customer customer: customerService.customerTransactionMap.keySet()){
            if(customer.getId() == 1L){
                assertEquals("John", customer.getFirstName());
                assertEquals("A", customer.getLastName());
            }
        }
    }
    @Test
    public void checkTransaction(){ // by checking if exists.
        for(Customer customer: customerService.customerTransactionMap.keySet()){
            if(customer.getId() == 1L){
                assertEquals(120L, customerService.customerTransactionMap.get(customer).stream().mapToLong(t -> t.getAmount()).sum());
            }
        }
    }

}
