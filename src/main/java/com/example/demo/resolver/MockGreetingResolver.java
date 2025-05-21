package com.example.demo.resolver;

import org.springframework.stereotype.Service;

@Service
public class MockGreetingResolver implements GreetingResolver {
    @Override
    public String resolveGreeting(String greetingKey) {
        return "Hello World!";
    }
}