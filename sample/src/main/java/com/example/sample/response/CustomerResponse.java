package com.example.sample.response;

import lombok.Data;

@Data
public class CustomerResponse {
    // this is the format of CustomerResponse
    private Long cid;
    private int status;
    private Long rewards;
    private String message;
}
