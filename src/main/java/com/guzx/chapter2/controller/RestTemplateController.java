package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.UserService;
import com.guzx.chapter2.vo.ResultVo;
import com.guzx.chapter2.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @GetMapping("/getUser")
    @ResponseBody
    public static UserVo getUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        UserVo userVo = restTemplate.getForObject("http://localhost/user/{id}", UserVo.class, id);
        System.out.println(userVo.getUserName());
        return userVo;
    }

    @ResponseBody
    @GetMapping("/getUsers")
    public static List<UserVo> getUsers(String userName, String note, int start, int limit) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("note", note);
        params.put("start", start);
        params.put("limit", limit);
        String url = "http://localhost/user/user/{userName}/{note}/{start}/{limit}";

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, params);
        List<UserVo> userVos = responseEntity.getBody();
        return userVos;

    }

    @ResponseBody
    @GetMapping("/restInsertUser")
    public static User insertUser(UserVo userVo) {
        // 请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求内容为json类型
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 请求实体对象
        HttpEntity<UserVo> httpEntity = new HttpEntity<>(userVo, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // 请求时传递实体对象，并返回回填id的用户
        // post请求需要设置请求头
        /*User user = restTemplate.postForObject("http://localhost/user/user", httpEntity, User.class);
        return user;*/

        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost/user/user", httpEntity, User.class);
        // 获取响应体
        User user = responseEntity.getBody();
        // 获取响应头
        HttpHeaders responseHead = responseEntity.getHeaders();
        List<String> success = responseHead.get("success");
        int status = responseEntity.getStatusCodeValue();
        System.out.println(user.getUserName());
        return user;
    }

    @GetMapping("/restDeleteUser")
    @ResponseBody
    public static void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        // delete方法没有返回值
        restTemplate.delete("http://localhost/user/user/{id}", id);
    }

}
