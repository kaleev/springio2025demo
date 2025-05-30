package com.example.demo.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoTransactionLevel4Service implements TransactionLevel4Service {

    private static final Logger log = LoggerFactory.getLogger(DemoTransactionLevel4Service.class);
    private final TransactionLevel5Service transactionLevel5Service;

    @Autowired
    public DemoTransactionLevel4Service(TransactionLevel5Service transactionLevel5Service) {
        this.transactionLevel5Service = transactionLevel5Service;
    }

    @Transactional
    public void processTransactionLevel4(String password) {
        log.info("Processing transaction at level 4");
        transactionLevel5Service.processTransactionLevel5(password);
    }
}