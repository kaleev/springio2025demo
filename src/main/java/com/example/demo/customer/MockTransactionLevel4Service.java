package com.example.demo.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class MockTransactionLevel4Service implements TransactionLevel4Service {

    private static final Logger log = LoggerFactory.getLogger(MockTransactionLevel4Service.class);
    private final TransactionLevel5Service transactionLevel5Service;
    private final CustomerService customerService;


    @Autowired
    public MockTransactionLevel4Service(TransactionLevel5Service transactionLevel5Service, CustomerService customerService) {
        this.transactionLevel5Service = transactionLevel5Service;
        this.customerService = customerService;
    }

    @Transactional
    public void processTransactionLevel4(String password) {
        log.info("Processing transaction at level 4");
        customerService.createRandomCustomer(password);
        transactionLevel5Service.processTransactionLevel5(password);
    }
}