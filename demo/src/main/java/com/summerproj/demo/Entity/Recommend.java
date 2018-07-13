package com.summerproj.demo.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Recommend{
    @Id
    @GeneratedValue
    private Integer id;

    private String start;
    private String end;
    private Integer startid;
    private String endtel;
    private String reason;

}
