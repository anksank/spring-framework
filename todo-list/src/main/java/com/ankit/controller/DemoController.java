package com.ankit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("welcome")
    public String welcome(Model model) {

        model.addAttribute("user", "Ankit");
        log.info("model= {}", model);

        // prefix + name + suffix is returned from this method because of the ViewResolver
        return "welcome";
    }

    // executed before any other method, every time the controller receives a request
    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        log.info("welcomeMessage() called");

        return "Welcome to demo application";
    }
}
