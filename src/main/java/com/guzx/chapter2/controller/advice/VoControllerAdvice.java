package com.guzx.chapter2.controller.advice;

import com.guzx.chapter2.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = {"com.guzx.chapter2.controller.*"}, annotations = Controller.class)
public class VoControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // 拦截指定类型的异常
    @ExceptionHandler(value = NotFoundException.class)
    public Map<String, Object> exception(HttpServletRequest request, NotFoundException exception) {
        Map<String, Object> errorMsg = new HashMap<>();
        errorMsg.put("code", exception.getCode());
        errorMsg.put("msg", exception.getCustomMsg());
        return errorMsg;
    }

}
