package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Deprecated
class SpringCloudFunctionAwsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void reverseString() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/reverseString")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Hello spring function"))
                .andReturn();
        mockMvc.perform(asyncDispatch(result)).andExpect(content().string("noitcnuf gnirps olleH"));
    }

    @Test
    void greet() throws Exception {
        String message = "World";
        MvcResult result = this.mockMvc.perform(post("/greeter")
                .contentType(MediaType.TEXT_PLAIN)
                .content(message)).andReturn();
        mockMvc.perform(asyncDispatch(result)).andExpect(content()
                .string("Hello " + message + ", and welcome to Spring Cloud Function!!!"));
    }
}
