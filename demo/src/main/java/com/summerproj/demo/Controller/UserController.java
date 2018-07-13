package com.summerproj.demo.Controller;


import com.summerproj.demo.Entity.*;
import com.summerproj.demo.Now;
import com.summerproj.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassageRepository passageRepository;
    @Autowired
    private RecommendRepository recommendRepository;

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
        Now.prework(model);


        return "user_home";
    }


    /**
     * 文案编制页面
     */
    @GetMapping(value = "/write")
    public String passageAddP1(Model model){
        Now.prework(model);

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
        passage.setRole(Now.getUser().getRole());
        passageRepository.save(passage);

        jumpMessage = "编制成功！将跳转个人主页";
        jumpUrl = "/user/index";
        return "redirect:/user/jump";
    }

    /**
     * 用户个人主页-注销
     */
    @GetMapping(value = "/logout")
    public String userLogOut(){
        if(Now.getUser()!=null){
            Now.getUser().setLogin(false);//注销状态
            userRepository.save(Now.getUser());
        }
        Now.setUser(null);

        return "redirect:/";
    }

    /**
     * 用户个人主页-推荐
     */
    @GetMapping(value = "/recommend")
    public String userRecP(Model model){
        Now.prework(model);

        return "refer";
    }
    @PostMapping(value = "/recommend")
    public String userRec(@RequestParam("tel") String i_tel,
                          @RequestParam("name") String i_name,
                          @RequestParam("reason") String i_reason){
        Recommend recommend = new Recommend();
        if (i_name==null || i_tel==null)
            return "redirect:/user/recommend";
        recommend.setEnd(i_name);
        recommend.setStart(Now.getUser().getName());
        recommend.setEndtel(i_tel);
        recommend.setReason(i_reason);

        recommendRepository.save(recommend);

        jumpUrl="/user/index";
        jumpMessage="推荐成功！返回个人主页。";
        return "redirect:/user/jump";
    }

    /**
     * 用户个人主页-审批文档
     */
    @GetMapping(value = "/check/passage")
    public String userCheckPassageP(Model model){
        Now.prework(model);
        List<Passage> tar = passageRepository.findAllByRoleLessThanEqualOrderByRole(Now.getUser().getRole()-1);
        if(tar.size()>=5)
            tar=tar.subList(0,4);
        model.addAttribute("result",tar);

        return "check_passage";
    }
    @GetMapping(value = "/check/passage/{id}")
    public String userCheckPassagePT( @PathVariable("id") Integer passId){
        if (Now.getUser().getRole()<2||Now.getUser()==null)
            return "redirect:/";

        Passage tar=passageRepository.findById(passId).get();
        tar.setRole(Now.getUser().getRole());
        passageRepository.save(tar);

        return "redirect:/user/check/passage";
    }

    /**
     * 用户个人主页-审批用户
     */
    @GetMapping(value = "/check/user")
    public String userCheckUserP(Model model){
        Now.prework(model);

        List<User> tar = userRepository.findAllByRoleIs(Now.getUser().getRole()-2);
        if(tar.size()>=5)
            tar=tar.subList(0,4);
        model.addAttribute("result",tar);

        return "check_user";
    }
    @GetMapping(value = "/check/user/{id}")
    public String userCheckUserPT( @PathVariable("id") Integer passId){
        if (Now.getUser().getRole()<2||Now.getUser()==null)
            return "redirect:/";

        User tar=userRepository.findById(passId).get();
        tar.setRole(Now.getUser().getRole()-1);
        userRepository.save(tar);


        return "redirect:/user/check/user";
    }
}
