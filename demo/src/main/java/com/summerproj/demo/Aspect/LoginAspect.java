package com.summerproj.demo.Aspect;

import com.summerproj.demo.Controller.UserController;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
    @Pointcut("execution(public * com.summerproj.demo.Controller.UserController.userHomeP(..))")
    public void login(){
        System.out.println("\n\n进入个人界面，必须是已登陆!\n\n");
    }

    @Before("login()")
    public void doBefore(){
        UserController uc = new UserController();
        if (uc.getNowUserLogin()){
            return;//
        }
    }

    @After("login()")
    public void doAfter(){
        UserController uc = new UserController();
        //注销状态
    }
}
