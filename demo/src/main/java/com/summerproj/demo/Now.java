package com.summerproj.demo;

import com.summerproj.demo.Entity.User;
import org.springframework.ui.Model;


public class Now{
    private static User user = null;

    public static User getUser() { return user;    }
    public static void setUser(User i_user) { Now.user = i_user;    }

    public Now(){
    }

    public static void prework(Model model) {
        if (Now.getUser() == null) {
            User temp = new User();
            temp.setLogin(false);
            temp.setGender("unknown");
            temp.setUsername("unknown");
            temp.setName("unknown");
            temp.setPassword("unknown");
            temp.setId(0);
            temp.setRole(-1);
            Now.setUser(temp);
        }
        model.addAttribute("nowUsername", Now.getUser().getUsername());
        model.addAttribute("nowCase", Now.getUser().getRole());
        model.addAttribute("nowUserid", Now.getUser().getId());

        if (Now.getUser().getRole() >= 2)
            model.addAttribute("nowUserrole", "管理员");
        else if (Now.getUser().getRole() == 1)
            model.addAttribute("nowUserrole", "会员");
        else if (Now.getUser().getRole() == 0)
            model.addAttribute("nowUserrole", "游客");
        else
            model.addAttribute("nowUserrole", "未登陆");
        return;
    }
}
