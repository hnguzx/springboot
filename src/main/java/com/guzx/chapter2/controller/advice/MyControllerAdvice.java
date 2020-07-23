package com.guzx.chapter2.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

//@ControllerAdvice(basePackages = {"com.guzx.chapter2.controller.*"}, annotations = Controller.class)
public class MyControllerAdvice {

    // 绑定格式化，参数转换规则，增加校验器
    @InitBinder
    public void initDataBinder(WebDataBinder binder) {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    // 在执行控制器之前执行，可以初始化数据模型
    @ModelAttribute
    public void projectModel(Model model) {
        model.addAttribute("project_name", "chapter2");
    }

    // 异常处理，使被拦截的控制器发生异常时都能使用相同的视图响应
    @ExceptionHandler(value = Exception.class)
    public String exception(Model model, Exception exception) {
        model.addAttribute("exception_message", exception.getMessage());
        return "exception";
    }
}
