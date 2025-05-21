package com.example.demo.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * A debug implementation of GreetingResolver that includes the IP address
 * along with the username for debugging purposes.
 */
@Service
public class DebugGreetingResolver implements GreetingResolver {

    private final GreetingResolver delegate;

    @Autowired
    public DebugGreetingResolver(GreetingResolver delegate) {
        this.delegate = delegate;
    }

    @Override
    public String resolveGreeting(String greetingKey) {
        String greeting = delegate.resolveGreeting(greetingKey);

        // Try to get the current request
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String ipAddress = request.getRemoteAddr();
            return greeting + " [IP: " + ipAddress + "]";
        }

        return greeting + " [IP: unknown]";
    }
}