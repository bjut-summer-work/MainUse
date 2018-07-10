package com.summerproj.demo.Controller;


import com.summerproj.demo.User;
import com.summerproj.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("singleton")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    User nowUser=null;
    public int getNowUserRole(){if(nowUser!=null)return nowUser.getRole(); else return -1;}
    public boolean getNowUserLogin(){if(nowUser!=null) return nowUser.getLogin(); else return false;}

    /**
     * 用户／管理员个人主页
     */
    @GetMapping(value = "/{id}")
    public String userHomeP(@PathVariable ("id") Integer nowId, Model model){
        model.addAttribute("nowUsername",nowUser.getUsername());
        model.addAttribute("nowUserrole",nowUser.getRole());

        if(nowUser.getRole()>=2) {
            model.addAttribute("nowUserrole","管理员");
            return "admin_home"; //admin
        }else if(nowUser.getRole()==1) {
            model.addAttribute("nowUserrole","会员");
            return "user_home"; //member
        }else {
            model.addAttribute("nowUserrole","游客");
            return "user_home"; //guest
        }

    }

}
