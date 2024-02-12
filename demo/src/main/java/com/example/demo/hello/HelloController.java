package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @Autowired
    private HelloService service;

    @GetMapping("/hello")
    public String getString(Model model) {
        return "hello";
    }

    @PostMapping("/hello")
    public String postResponse(@RequestParam("text1") String str, Model model) {
        model.addAttribute("text1", str);
        return "hello/response";
    }

    @PostMapping("/hello/db")
    public String postEmployee(@RequestParam("text2") String id, Model model) {
        try {
            Employee employee = service.getEmployee(id);
            model.addAttribute("employee", employee);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "hello/db";
    }

}
