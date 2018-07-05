package com.example.demo.service.impl;

import com.example.demo.dao.RegDao;
import com.example.demo.entity.User;
import com.example.demo.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegServicelmpl implements RegService {

    @Autowired
    public RegDao regdao;

    @Override
    public int insertUser(User user){
        return regdao.insertUser(user);
    }

}
