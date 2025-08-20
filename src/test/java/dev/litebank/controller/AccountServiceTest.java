package dev.litebank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.litebank.dto.PaymentMethod;
import dev.litebank.dto.requests.DepositRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
public class AccountServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCanPostDeposit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("0123456789");
        depositRequest.setAmount(new BigDecimal(20000));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);
        String json =  objectMapper.writeValueAsString(depositRequest);
        String depositEndpoint = "/api/v1/account";
        mockMvc.perform(MockMvcRequestBuilders.post(depositEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
}
