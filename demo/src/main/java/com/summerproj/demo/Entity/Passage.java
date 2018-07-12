package com.summerproj.demo.Entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Passage {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String author;
    private String article;
    private Date date;
    private Integer type;
}
