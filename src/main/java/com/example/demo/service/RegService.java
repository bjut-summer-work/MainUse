package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface RegService {

    public int insertUser(User user);
}
