package com.guzx.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/i18n")
public class I18n {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/view")
    public String page(HttpServletRequest request) {
        // 获取国际化区域
        Locale locale = LocaleContextHolder.getLocale();
        // 获取国际化消息
        String msg = messageSource.getMessage("msg", null, locale);
        System.out.println("msg=" + msg);
        return "i18n/internationalization";

    }
}
