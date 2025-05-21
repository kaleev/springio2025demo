package com.example.demo;

import com.example.demo.customer.CustomerService;
import com.example.demo.customer.TransactionLevel1Service;
import com.example.demo.resolver.GreetingResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class HomeController {

    private final GreetingResolver greetingResolver;
    private final CustomerService customerService;
    private final TransactionLevel1Service transactionLevel1Service;

    @Value("${greetingKey:default}")
    private String greetingKey;

    @Autowired
    public HomeController(GreetingResolver GreetingResolver, CustomerService customerService, TransactionLevel1Service transactionLevel1Service) {
        this.greetingResolver = GreetingResolver;
        this.customerService = customerService;
        this.transactionLevel1Service = transactionLevel1Service;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        // Header message
        String message = "Welcome!";

        // Greeting message
        String Greeting = greetingResolver.resolveGreeting(greetingKey);

        // Get all customers
        var customers = customerService.getAllCustomers();

        // Add attributes to the model
        model.addAttribute("message", message);
        model.addAttribute("greeting", Greeting);
        model.addAttribute("customers", customers);

        // Return the view name (index.html)
        return "index";
    }

    @PostMapping("/add-random-customer")
    public String addRandomCustomer() {
        transactionLevel1Service.startTransactionChain(UUID.randomUUID().toString().substring(0, 10));
        return "redirect:/";
    }


}
