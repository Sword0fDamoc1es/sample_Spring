package com.example.sample.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.sample.repository.CustomerRepository;
import com.example.sample.response.CustomerResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ControllerAdvice
public class GlocalExceptionHandler {
    // currently this file only handles NumberFormatException.
    private static final Logger logger = LogManager.getLogger(GlocalExceptionHandler.class);

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<CustomerResponse> handleNumberFormatException(NumberFormatException e) {
        // log here. 
        logger.error("Invalid customer id", e);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setStatus(400);
        customerResponse.setRewards(0L);
        customerResponse.setMessage("Invalid customer id");
        return ResponseEntity.badRequest().body(customerResponse);
    }
}
