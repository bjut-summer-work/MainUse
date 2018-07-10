package com.summerproj.demo.Controller;


import com.summerproj.demo.Now;
import com.summerproj.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 用户个人主页
     */
    @GetMapping(value = {"/index"})
    public String userHomeP(Model model){
        if(Now.getUser()==null)
            return "redirect:/";

        model.addAttribute("nowUsername",Now.getUser().getUsername());
        model.addAttribute("nowCase",Now.getUser().getRole());

        if(Now.getUser().getRole()>=2) {
            model.addAttribute("nowUserrole","管理员");
            return "user_home"; //admin
        }else if(Now.getUser().getRole()==1) {
            model.addAttribute("nowUserrole","会员");
            return "user_home"; //member
        }else {
            model.addAttribute("nowUserrole","游客");
            return "user_home"; //guest
        }
    }

    /**
     * 用户个人主页-注销
     */
    @GetMapping(value = "/logout")
    public String userLogOut(){
        Now.getUser().setLogin(false);//注销状态
        userRepository.save(Now.getUser());
        Now.setUser(null);
        return "redirect:/";
    }

}
