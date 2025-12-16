package org.example.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "age", required = false) Integer age) {
        System.out.println("ageParam" + age);
        return "redirect:https://google.com";   /**  http://localhost:8080/hello  */
    }

}
