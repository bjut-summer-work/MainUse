package com.summerproj.demo.Controller;

import com.summerproj.demo.Entity.User;
import com.summerproj.demo.Repository.UserRepository;
import com.summerproj.demo.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.*;


@Controller
public class SignController {
    @Autowired
    private UserRepository userRepository;

    private String jumpMessage="返回主页";
    private String jumpUrl="/home";

    /**
     * jump页面
     */
    @GetMapping(value = "/jump")
    public String userjumpP(Model model){
        model.addAttribute("jumpMessage",jumpMessage);
        model.addAttribute("jumpUrl",jumpUrl);
        return "jump";
    }

    /**
     * 用户注册页面
     */
    @GetMapping(value = "/regist")
    public String userAddP(Model model){
        return "regist";
    }
    @Transactional
    @PostMapping(value = "/regist")
    public String userAdd(@RequestParam("name") String i_name,
                          @RequestParam("gender") String i_gender,
                          @RequestParam("password") String i_password,
                          @RequestParam("username") String i_username,
                          @RequestParam("tel") String i_tel,
                          @RequestParam("address") String i_address,
                          @RequestParam("birthday") String i_birthday,
                          @RequestParam("friend") String i_friend,
                          @RequestParam("company") String i_company){
        User user = new User();

        user.setName(i_name);
        user.setGender(i_gender);

        if(i_username.isEmpty()){
            jumpMessage = "注册失败（用户名不可为空）将重新注册";
            jumpUrl = "/regist";
            return "redirect:/jump";
        }user.setUsername(i_username);


        if(i_password.isEmpty()){
            jumpMessage = "注册失败（密码不可为空）将重新注册";
            jumpUrl = "/regist";
            return "redirect:/jump";
        }user.setPassword(i_password);

        user.setAddress(i_address);

        Date conv_date;
        try {
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            conv_date = fmt.parse(i_birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            jumpMessage = "注册失败（日期格式错误）将重新注册";
            jumpUrl = "/regist";
            return "redirect:/jump";
        }user.setBirthday(conv_date);

        user.setTel(i_tel);
        user.setFriend(i_friend);
        user.setCompany(i_company);
        user.setLogin(false);   //默认未登陆
        user.setRole(0);        //默认游客权限
        userRepository.save(user);

        jumpMessage = "注册成功！将跳转登陆界面";
        jumpUrl = "/login";
        return "redirect:/jump";
    }


    /**
     * 用户登陆页面
     */
    @GetMapping(value = "/login")
    public String userLoginP(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String userLogin(@RequestParam("password") String i_password,
                          @RequestParam("username") String i_username){
        Optional<User> userList = userRepository.findByUsername(i_username);
        if (userList.isPresent()){
            User user = userList.get();
            if(user.getPassword().equals(i_password)) {
                jumpMessage = "登陆成功！将跳转个人主页";
                jumpUrl = "/user/";

                user.setLogin(true);
                userRepository.save(user);

                Now.setUser(user);


                return "redirect:/jump";
            }else{
                jumpMessage = "密码错误！请重新登陆";
                jumpUrl = "/login";
                return "redirect:/jump";
            }
        }else {
            jumpMessage = "空用户！请重新登陆";
            jumpUrl = "/login";
            return "redirect:/jump";
        }
    }


    /*
    //id查询一个用户
    @GetMapping(value = "/user/{id}")
    public Optional<User> userFind(@PathVariable("id") Integer i_id){
        return userRepository.findById(i_id);
    }
    //姓名查询一个用户
    @GetMapping(value = "/user/name/{name}")
    public Optional<User> userFindName(@PathVariable("name") String i_name){
        return userRepository.findByNameContains(i_name);
    }

    //更新一个用户
    @Transactional
    @PutMapping(value = "/user/{id}")
    public User userUpdate(@PathVariable("id") Integer i_id,
                           @RequestParam("name") String i_name,
                           @RequestParam("gender") String i_gender,
                           @RequestParam("password") String i_password,
                           @RequestParam("username") String i_username,
                           @RequestParam("tel") String i_tel,
                           @RequestParam("address") String i_address,
                           @RequestParam("birthday") String i_birthday,
                           @RequestParam("friend") String i_friend,
                           @RequestParam("company") String i_company){
        User user = new User();
        user.setId(i_id);
        user.setName(i_name);
        user.setGender(i_gender);
        user.setUsername(i_username);
        user.setPassword(i_password);

        return userRepository.save(user);
    }

    //删除一个用户
    @DeleteMapping(value = "/user/{id}")
    public void userDelete(@PathVariable("id") Integer i_id){
        userRepository.deleteById(i_id);
    }
    */

}
