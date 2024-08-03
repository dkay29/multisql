package com.dkay229.multisql.rest.server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@WebMvcTest(MultiSqlServerController.class)
public class MultiSqlServerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String jwtToken;
    @BeforeEach
    public void setUp() {
        // Set up a valid JWT token. In a real-world scenario, you might need to generate this token using your
        // authentication service.
        jwtToken = "your-jwt-token";
    }
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testPing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Pong!"));
    }
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testListTables() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/listTables"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"tableNames\":[\"tab1\",\"tab2\"]}"));
    }
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testExecuteSql() throws Exception {
        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/executeSql2")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .content("select * from table1;"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"tableNames\":[\"tab1\",\"tab2\"]}"));
    }
}
