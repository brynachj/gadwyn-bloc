package com.brynachj.gadwynbloc.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello-world")
    fun getBlockchain(): String {
        return "hello gadwyn-bloc"
    }
}