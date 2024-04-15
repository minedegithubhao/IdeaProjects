package com.example.idrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * @author cxdpc
 * @date 2024/4/12 09:48
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IdrmApplicationTestBase {

    @Resource
    public MockMvc mockMvc;

    @Resource
    public ObjectMapper objectMapper;
}