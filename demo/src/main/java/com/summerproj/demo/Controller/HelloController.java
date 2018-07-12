package com.summerproj.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /**
     * index页面
     */
    @GetMapping(value = {"/","/hello","/hi","/index","/home"})
    public String mainIndex(){
        return "index";
    }
    @GetMapping(value = {"/user","/data"})
    public String userIndex(){
        return "redirect:/user/index";
    }

}
