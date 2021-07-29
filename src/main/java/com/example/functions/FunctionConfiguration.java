package com.example.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class FunctionConfiguration {

    private Logger logger = LoggerFactory.getLogger(FunctionConfiguration.class);

//    Map<String, String> headers = new HashMap<>();
//            headers.put("X-Powered-By", "AWS Lambda & Serverless");
//            headers.put("Content-Type", "application/json");
    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    @Bean
    public Supplier<String> health() {
        return () -> "OK";
    }


    @Bean
    public Consumer<String> processMessage() {
        return msg -> {
            logger.info("message, '{}' received for processing.", msg);
        };
    }

//    @Bean
//    public Supplier<Flux<String>> words() {
//        return () -> Flux.fromArray(new String[] {"foo", "bar"});
//    }
}
