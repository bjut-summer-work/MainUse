package com.summerproj.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /**
     * index页面
     */
    @GetMapping(value = {"/","/home"})
    public String say(){
        return "index";
    }

}
