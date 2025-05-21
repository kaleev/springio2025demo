package com.example.demo.resolver;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * A simple implementation of GreetingResolver that maps usernames to real names
 * using a predefined mapping.
 */
@Service
@Profile("dev")
@Primary
public class SimpleGreetingResolver implements GreetingResolver {

    private final Map<String, String> map = Map.of(
        "default", "Hello World",
        "starwars", "May the Force be with you",
        "yoda", "Do or do not, there is no try",
        "spock", "Live long and prosper",
        "gandalf", "A wizard is never late, nor is he early",
        "bond", "The name's Bond, James Bond",
        "terminator", "Hasta la vista, baby",
        "morpheus", "Welcome to the real world",
        "vader", "I find your lack of faith disturbing",
        "lotr", "You shall not pass!"
    );

    @Override
    public String resolveGreeting(String greetingKey) {
        return map.getOrDefault(greetingKey, greetingKey);
    }
}
