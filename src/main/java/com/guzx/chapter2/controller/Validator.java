package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.pojo.ValidatorPojo;
import com.guzx.chapter2.validate.UserValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/validator")
public class Validator {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 绑定校验器
        binder.setValidator(new UserValidator());
        // 定义日期参数格式，参数不需要再注解@DateTimeFormat,boolean参数表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @RequestMapping("/view")
    public String validatorPage() {
        return "/validator/pojo";
    }

    @ResponseBody
    @RequestMapping("/validator")
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo pojo, Errors errors) {
        Map<String, Object> result = new HashMap<>();
        List<ObjectError> objectErrors = errors.getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String key = null;
            String value = null;
            // 字段错误
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                key = fieldError.getField();
            } else { // 非字段错误
                key = objectError.getObjectName();
            }

            value = objectError.getDefaultMessage();
            result.put(key, value);
        }

        return result;
    }

    @GetMapping("/customValidator")
    @ResponseBody
    public Map<String, Object> customValidator(@Valid User_MyBatis user_myBatis, Errors errors, Date date) {
        Map<String, Object> result = new HashMap<>();
        result.put("user", user_myBatis);
        result.put("date", date);
        if (errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError error : errorList) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    result.put(fieldError.getField(), fieldError.getDefaultMessage());
                } else {
                    result.put(error.getObjectName(), error.getDefaultMessage());
                }
            }
        }
        return result;
    }
}
