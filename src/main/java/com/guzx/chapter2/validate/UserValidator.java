package com.guzx.chapter2.validate;

import com.guzx.chapter2.pojo.User_MyBatis;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User_MyBatis.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o == null) {
            errors.rejectValue("", null, "用户不能为空");
        }

        User_MyBatis user_myBatis = (User_MyBatis) o;
        if (StringUtils.isEmpty(user_myBatis.getUserName())) {
            errors.rejectValue("", null, "用户名不能为空");
        }
    }
}
