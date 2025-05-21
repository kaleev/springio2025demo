package com.example.demo.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionLevel5Service {

    private static final Logger log = LoggerFactory.getLogger(TransactionLevel5Service.class);
    private final CustomerService customerService;

    @Autowired
    public TransactionLevel5Service(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processTransactionLevel5(String password) {
        log.info("Processing transaction at level 5");
        customerService.createRandomCustomer(password);
    }
}