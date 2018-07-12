package com.summerproj.demo;

import com.summerproj.demo.Entity.User;


public class Now{
    private static User user = null;

    public static User getUser() { return user;    }
    public static void setUser(User i_user) { Now.user = i_user;    }

    public Now(){
    }
}
