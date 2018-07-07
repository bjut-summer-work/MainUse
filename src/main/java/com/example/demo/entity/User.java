package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    public int id;
    public String name;
    public String sex;
    public Date birthday;
    public String address;
    public String contact;
    public String refer;
    public String branch;
    public String pro_group;
    public String username;
    public String password;
    public int role;
    public int check;
}
