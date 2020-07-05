package com.guzx.chapter2.pojo;

import com.guzx.chapter2.converter.SexConverter;
import com.guzx.chapter2.enumeration.SexEnum;

//import javax.persistence.*;

//@Entity(name = "user_jpa")
//@Table(name = "t_user")
public class User_JPA {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "user_name")
    private String userName;

    // 枚举
//    @Convert(converter = SexConverter.class)
    private SexEnum sex;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
