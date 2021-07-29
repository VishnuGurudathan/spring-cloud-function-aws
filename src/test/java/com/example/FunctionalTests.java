package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;


@FunctionalSpringBootTest
class FunctionalTests {

    @Autowired
    private FunctionCatalog catalog;


    @Test
    void health() throws Exception {
        Supplier<String> supplier = catalog.lookup(Function.class,
                "health");
        assertThat(supplier.get()).isEqualTo("OK");
    }

    @Test
    void reverseString() throws Exception {
        Function<String, String> function = catalog.lookup(Function.class,
                "reverseString");
        assertThat(function.apply("hello")).isEqualTo("olleh");
    }

    @Test
    void greet() throws Exception {
        String message = "World";

        String result = "Hello " + message + ", and welcome to Spring Cloud Function!!!";

        Function<String, String> function = catalog.lookup(Function.class,
                "greeter");
        assertThat(function.apply(message)).isEqualTo(result);
    }

    @Test
    void words() throws Exception {

        Flux<String> words = Flux.fromArray(new String[] {"Spring MVC", "Spring Boot", "Spring Web"});
        Supplier<Flux<String>> supplier = catalog.lookup(Function.class,
                "words");
        StepVerifier.create(words.log())
                .expectNext("Spring MVC")
                .expectNext("Spring Boot")
                .expectNext("Spring Web")
                .verifyComplete();
    }
}
