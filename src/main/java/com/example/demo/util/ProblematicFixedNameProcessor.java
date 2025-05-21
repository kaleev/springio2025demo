package com.example.demo.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class ProblematicFixedNameProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {
        Map<String, Object> map = new HashMap<>();
        map.put("user.random.names", "false");
        env.getPropertySources().addFirst(new MapPropertySource("myProcessorProps", map));
    }
}
