package com.brynachj.gadwynbloc.controllers

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@RunWith(SpringRunner::class)
@WebMvcTest(HelloController::class)
class HelloControllerTest {

    private val HELLO_RESPONSE: String = "hello gadwyn-bloc"
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getHelloEndpointCallsHelloService() {
        mockMvc.get("/hello-world").andExpect {
            status { isOk }
            content { string(HELLO_RESPONSE) }
        }
    }
}