package ru.balcon.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/api/echo/")
public class EchoController {
    //    @RequestParam (Map<String,String> allRequestParams, ModelMap model)
    @PostMapping("/")
    String echo_method(@RequestParam Map<String,String> allRequestParameter,Model model){
        model.addAttribute("allRequestParameter",allRequestParameter);
        return "echo/echo";
    }

}
