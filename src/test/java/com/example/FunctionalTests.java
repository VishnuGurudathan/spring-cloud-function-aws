package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;


@FunctionalSpringBootTest
public class FunctionalTests {
    @Autowired
    private FunctionCatalog catalog;

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
}
