package com.summerproj.demo.Entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String gender;
    private String username;
    private String password;

    private String tel;
    private String address;
    private Date birthday;

    private String friend;
    private String company;

    private Integer role;
    private Boolean Login;
    private Boolean confirm;

    public User(){
    }
}
