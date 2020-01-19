package com.brynachj.kotlin.blockchainspike

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.parsing.Parser
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class ApplicationTests {

    @Test
    fun basicTest() {

        RestAssured.registerParser("text/plain", Parser.JSON)

        val body = given().get("/hello-world")
                .getBody().asString()

        assertThat(body).isEqualTo("hello gadwyn-bloc")
    }

}
