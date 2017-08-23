package com.lfo.demo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping



@Controller
class Home {
    @RequestMapping("/")
    @ResponseBody
    fun home(): String {
        return "Hello World!"
    }
}