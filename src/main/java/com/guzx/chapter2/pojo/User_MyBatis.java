package com.guzx.chapter2.pojo;

import com.guzx.chapter2.converter.SexConverter;
import com.guzx.chapter2.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Alias(value = "user_my")
public class User_MyBatis {
    private Long id;
    private String userName;
    // 枚举
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

    public User_MyBatis() {
    }
}
