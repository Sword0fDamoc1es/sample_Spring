package com.example.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.exception.GlocalExceptionHandler;
import com.example.sample.response.CustomerResponse;
import com.example.sample.service.CustomerService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}/rewards")
    public ResponseEntity<CustomerResponse> getRewards(@PathVariable("customerId") String customerIdStr) {
        try{
            Long customerId = Long.parseLong(customerIdStr);
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setCid(customerId);

            Long rewards = customerService.getRewards(customerId);
            if(rewards == -404L){
                customerResponse.setStatus(404);
                customerResponse.setRewards(0L);
                customerResponse.setMessage("Customer not found or no transaction of the customer.");
                return ResponseEntity.status(404).body(customerResponse);
            }
            else{
                customerResponse.setStatus(200);
                customerResponse.setRewards(rewards);
                customerResponse.setMessage("Success");
                return ResponseEntity.status(200).body(customerResponse);
            }
        }catch(NumberFormatException e){
            GlocalExceptionHandler glocalExceptionHandler = new GlocalExceptionHandler();
            return glocalExceptionHandler.handleNumberFormatException(e);
        }

    }
    @GetMapping("/${initialize.initHash}/data")
    public void initalizeFixedFakeDB() {
        customerService.setUpFixedFakeData();
    }
    
}
