package com.krisksama.codingassessment.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class SignalControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testExecuteSignal1() throws Exception {
        mockMvc.perform(get("/execute").queryParam("signal", "1")).andExpect(status().isOk());
    }

    @Test
    void testExecuteSignal2() throws Exception {
        mockMvc.perform(get("/execute").queryParam("signal", "2")).andExpect(status().isOk());
    }

    @Test
    void testExecuteSignal3() throws Exception {
        mockMvc.perform(get("/execute").queryParam("signal", "3")).andExpect(status().isOk());
    }

    @Test
    void testExecuteSignal_invalid() throws Exception {
        mockMvc.perform(get("/execute").queryParam("signal", "abc")).andExpect(status().isBadRequest());
    }

}