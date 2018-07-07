package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegDao {

    @Insert({"insert into user(id,name,sex,birthday,address,contact,refer,branch,pro_group,username,password,role,check)"+
            "values(#{id},#{name},#{sex},#{birthday},#{address},#{contact},#{refer},#{branch},#{pro_group},#{username},#{password},#{role},#{check})"})
    public int insertUser(User user);
}
