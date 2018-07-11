package com.summerproj.demo.Controller;

import com.summerproj.demo.Repository.PassageRepository;
import com.summerproj.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/data")
public class DataController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassageRepository passageRepository;

    @GetMapping(value = "/search/user")
    public String searchUserP(){
        return "search_user";
    }
    @PostMapping(value = "/search/user")
    public String searchUser(){
        return "search_user";
    }


    @GetMapping(value = "/search/passage")
    public String searchPassageP(){
        return "search_user";
    }
    @PostMapping(value = "/search/passage")
    public String searchPassage(){
        return "search_user";
    }

}
