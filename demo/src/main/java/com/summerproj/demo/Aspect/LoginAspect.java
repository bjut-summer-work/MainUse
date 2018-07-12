package com.summerproj.demo.Aspect;

import com.summerproj.demo.Now;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {



    @Before("execution(public * com.summerproj.demo.Controller.UserController.userHomeP(..))")
    public void doBefore(){
        if(Now.getUser()!=null && Now.getUser().getLogin()==true)
            System.out.println("\n\n\n\n\t>>>> "+Now.getUser().getUsername()+" 合法访问主页\n\n\n\n");
        else
            System.out.println("\n\n\n\n\t>>>> 非法访问主页\n\n\n\n");
    }

    @After("execution(public * com.summerproj.demo.Controller.UserController.userLogOut(..))")
    public void doAfter(){
        System.out.println("\n\n\n\n\t>>>> 注销完毕\n\n\n\n");
    }
}
