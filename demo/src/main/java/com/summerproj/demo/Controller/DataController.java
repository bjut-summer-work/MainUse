package com.summerproj.demo.Controller;

import com.summerproj.demo.Entity.Passage;
import com.summerproj.demo.Entity.User;
import com.summerproj.demo.Now;
import com.summerproj.demo.Repository.PassageRepository;
import com.summerproj.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@RequestMapping(value="/data")
public class DataController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassageRepository passageRepository;
    

    /**
     * 搜用户
     */
    @GetMapping(value = "/search/user")
    public String searchUserP(Model model){
        Now.prework(model);

        return "search_user";
    }
    @PostMapping(value = "/search/user")
    public String searchUser(){
        return "search_user";
    }

    /**
     * 搜文章
     */
    @GetMapping(value = "/search/passage")
    public String searchPassageP( Model model){
        Now.prework(model);

        model.addAttribute("result",passageRepository.findAll());

        return "search_passage";
    }
    @GetMapping(value = "/search/passage/title/{na}")
    public String searchPassagePT(@PathVariable("na") String i_title, Model model){
        Now.prework(model);

        Optional<Passage> passageFind;
        passageFind = passageRepository.findByTitleContains(i_title);
        model.addAttribute("result",passageFind);

        return "index";
    }
    @PostMapping(value = "/search/passage")
    public String searchPassage(){
        return "search_passage";
    }


    /**
     * 显示用户
     */
    @GetMapping(value = "/user/{id}")
    public String infoUserP(Model model, @PathVariable("id") Integer userId){
        Now.prework(model);

        Optional<User> tempList = userRepository.findById(userId);
        User targetUser;
        if(tempList.isPresent())
            targetUser = tempList.get();
        else
            return "redirect:/data/search/user";
        model.addAttribute("target",targetUser);

        return "info_user";
    }
    /**
     * 编辑用户
     */
    @GetMapping(value = "/user/{id}/edit")
    public String editUserP(Model model, @PathVariable("id") Integer userId){
        Now.prework(model);

        Optional<User> tempList = userRepository.findById(userId);
        User targetUser;
        if(tempList.isPresent())
            targetUser = tempList.get();
        else
            return "redirect:/data/search/user";
        model.addAttribute("target",targetUser);

        return "edit_user";
    }
    @Transactional
    @PostMapping(value = "/user/{id}/edit")
    public String editUser(@PathVariable("id") Integer userId,
                           @RequestParam("name") String i_name,
                           @RequestParam("gender") String i_gender,
                           @RequestParam("password") String i_password,
                           @RequestParam("username") String i_username,
                           @RequestParam("tel") String i_tel,
                           @RequestParam("address") String i_address,
                           @RequestParam("friend") String i_friend,
                           @RequestParam("company") String i_company){
        Optional <User> list = userRepository.findById(userId);
        User user = list.get();

        System.out.println("\n\n\n\n\t>>>> 更新数据\n\n\n\n");
        System.out.println("\n\n\n\n\t>>>> "+i_gender+"\n\n\n\n");

        user.setName(i_name);
        user.setGender(i_gender);

        if(i_username.isEmpty()){
            return "redirect:/data/user/"+userId.toString()+"/edit";
        }user.setUsername(i_username);


        if(i_password.isEmpty()){
            return "redirect:/data/user/"+userId.toString()+"/edit";
        }user.setPassword(i_password);

        user.setAddress(i_address);

        user.setTel(i_tel);
        user.setFriend(i_friend);
        user.setCompany(i_company);

        userRepository.save(user);

        return "redirect:/data/user/"+userId.toString();
    }

    /**
     * 显示文章
     */
    @GetMapping(value = "/passage/{id}")
    public String infoPassageP(Model model, @PathVariable("id") Integer passageId){
        Now.prework(model);

        Optional<Passage> tempList = passageRepository.findById(passageId);
        Passage targetPassage;
        if(tempList.isPresent())
            targetPassage = tempList.get();
        else
            return "redirect:/data/search/passage";
        model.addAttribute("target",targetPassage);

        return "info_passage";
    }
    @PostMapping(value = "/passage/{id}")
    public String infoPassage(@PathVariable("id") String passageId){
        return "info_passage";
    }


}
