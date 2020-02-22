package com.ankit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    // prefix + name + suffix is returned from this method because of the ViewResolver
    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }
}
