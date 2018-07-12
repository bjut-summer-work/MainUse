package com.summerproj.demo.Controller;


import com.summerproj.demo.Entity.Passage;
import com.summerproj.demo.Now;
import com.summerproj.demo.Repository.PassageRepository;
import com.summerproj.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassageRepository passageRepository;

    private String jumpMessage="返回主页";
    private String jumpUrl="/home";

    /**
     * jump页面
     */
    @GetMapping(value = "/jump")
    public String passagejumpP(Model model){
        model.addAttribute("jumpMessage",jumpMessage);
        model.addAttribute("jumpUrl",jumpUrl);
        return "jump";
    }


    /**
     * 用户个人主页
     */
    @GetMapping(value = {"/index"})
    public String userHomeP(Model model){
        if(Now.getUser()==null)
            return "redirect:/";

        model.addAttribute("nowUsername",Now.getUser().getUsername());
        model.addAttribute("nowCase",Now.getUser().getRole());
        model.addAttribute("nowUserid",Now.getUser().getId());

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
     * 文案编制页面
     */
    @GetMapping(value = "/write")
    public String passageAddP1(Model model){
        if(Now.getUser()==null)
            return "redirect:/";

        model.addAttribute("nowUsername",Now.getUser().getUsername());
        model.addAttribute("nowCase",Now.getUser().getRole());

        return "write";
    }
    @Transactional
    @PostMapping(value = "/write")
    public String passageAdd(@RequestParam("title") String i_title,
                              @RequestParam("article") String i_article,
                              @RequestParam("type") Integer i_type) {
        Passage passage = new Passage();

        passage.setTitle(i_title);
        passage.setAuthor(Now.getUser().getUsername());

        passage.setDate(new Date());
        passage.setArticle(i_article);
        passage.setType(i_type);
        passageRepository.save(passage);

        jumpMessage = "编制成功！将跳转个人主页";
        jumpUrl = "/user/index";
        return "redirect:/jump";
    }

    /**
     * 用户个人主页-注销
     */
    @GetMapping(value = "/logout")
    public String userLogOut(){
        if(Now.getUser()==null)
            return "redirect:/";

        Now.getUser().setLogin(false);//注销状态
        userRepository.save(Now.getUser());
        Now.setUser(null);

        return "redirect:/";
    }

}
