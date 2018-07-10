package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.impl.RegServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegController {

    @Autowired
    public RegServicelmpl rsl;

    @RequestMapping(value = "", method = RequestMethod.GET)   // " "中填入注册页面的地址
    public String registUser(HttpServletRequest request) {
        User user = new User();
        user.name = request.getParameter("");     // " "中填入web前端注册页面中每一栏的名称
        user.sex = request.getParameter("");
        String date = request.getParameter("");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.birthday = f.parse(date);
            user.address = request.getParameter("");
            user.contact = request.getParameter("");
            user.refer = request.getParameter("");
            user.branch = request.getParameter("");
            user.pro_group = request.getParameter("");
            user.username=request.getParameter("");
            user.password=request.getParameter("");
            user.role=0;
            user.check=0;
            int row = rsl.insertUser(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";  // " "内填入注册成功界面的html文件
    }
}