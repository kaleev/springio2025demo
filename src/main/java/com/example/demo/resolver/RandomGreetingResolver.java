package com.example.demo.resolver;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * A random name generator implementation of GreetingResolver that generates
 * random display names for testing or demo purposes.
 */
@Service
@Profile("demo")
@Primary
public class RandomGreetingResolver implements GreetingResolver {

    private final Random random = new Random();

    private final List<String> greetings = List.of(
        "Hello World",
        "May the Force be with you",
        "Do or do not, there is no try",
        "Live long and prosper",
        "A wizard is never late, nor is he early",
        "The name's Bond, James Bond",
        "Hasta la vista, baby",
        "Welcome to the real world",
        "I find your lack of faith disturbing",
        "You shall not pass!"
    );

    @Override
    public String resolveGreeting(String greetingKey) {
        return greetings.get(random.nextInt(greetings.size()));
    }
}