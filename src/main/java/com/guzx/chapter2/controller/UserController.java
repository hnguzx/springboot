package com.guzx.chapter2.controller;

//import com.guzx.chapter2.dao.JpaUserRepository;

import com.alibaba.fastjson.JSONArray;
import com.guzx.chapter2.dao.JpaUserRepository;
import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.exception.NotFoundException;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.pojo.User_JPA;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.UserService;
import com.guzx.chapter2.service.impl.JdbcTemplImpl;
import com.guzx.chapter2.vo.ResultVo;
import com.guzx.chapter2.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplImpl jdbcTempl;

//    @Autowired
//    private JpaUserRepository jpaUserRepository;

    @Autowired
    private MyBatisUserService myBatisUserService;

    @RequestMapping("/add")
    @ResponseBody
    public int addUser(String user_name, int sex, String note) {
        User user = new User();
        user.setUserName(user_name);
        user.setNote(note);

        SexEnum sexEnum = SexEnum.getEnumById(sex);
        user.setSex(sexEnum);


        int result = jdbcTempl.insertUser(user);
        return result;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User addUser(int id) {
        User user = jdbcTempl.getUser2(id);
        return user;
    }

    @RequestMapping("/detail")
    public ModelAndView userDetail(int id) {
        User user = jdbcTempl.getUser2(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/detail");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @RequestMapping("/detailJson")
    public ModelAndView userDetail2(int id) {
        User user = jdbcTempl.getUser2(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/table")
    public ModelAndView getAllUser() {
//        List<User> users = jdbcTempl.findUsers(null, null);
//        List<User> users = jdbcTempl.findAll();
        List<User_MyBatis> users = myBatisUserService.getUsers(null, null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/table");
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    //    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PostMapping(value = "/list")
    @ResponseBody
    public List<User_MyBatis> getList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "note", required = false) String note) {
        List<User_MyBatis> userList = myBatisUserService.getUsers(userName, note);
        return userList;
    }

    /*@RequestMapping("/jpa")
    @ResponseBody
    public User_JPA getUser(Long id) {
        User_JPA user = jpaUserRepository.findById(id).get();
        return user;
    }*/

    /*@RequestMapping("/jpaFindUser")
    @ResponseBody
    public List<User_JPA> getUser2(String userName,String note) {
        List<User_JPA> user = jpaUserRepository.findUser_JPAs(userName,note);
        return user;
    }*/

    /*@RequestMapping("/getUser_JPAById")
    @ResponseBody
    public User_JPA getUser3(Long id) {
        User_JPA user = jpaUserRepository.getUser_JPAById(id);
        return user;
    }*/

    @RequestMapping("/getUser_mybatis")
    @ResponseBody
    public User_MyBatis getUser4(Long id) {
        User_MyBatis user = myBatisUserService.getUser(id);
        return user;
    }

    @GetMapping("/add")
    public String add() {
        return "/user/add";
    }

    @PostMapping("/insert")
    @ResponseBody
    public User_MyBatis insert(@RequestBody User_MyBatis user_myBatis) {
        myBatisUserService.insertUser(user_myBatis);
        return user_myBatis;
    }

    // {}标识占位符
    @GetMapping("/{id}")
    @ResponseBody
    // @PathVariable通过名称获取参数
    public User getUser5(@PathVariable("id") Integer id) {
        User user = jdbcTempl.getUser2(id);
        return user;
    }

    @GetMapping("/show")
    public String showUser(Long id, Model model) {
        User_MyBatis user_myBatis = myBatisUserService.getUser(id);
        model.addAttribute("user", user_myBatis);
        return "data/user";
    }

    @GetMapping("/show2")
    public String showUser2(User_MyBatis user_myBatis, Model model) {
        System.out.println(user_myBatis.getUserName());
        return "data/user";
    }

    @GetMapping("/redirect1")
    public String redirect(String userName, String note) {
        User_MyBatis user_myBatis = new User_MyBatis();
        user_myBatis.setUserName(userName);
        user_myBatis.setNote(note);
        myBatisUserService.insertUser(user_myBatis);
        return "redirect:/user/show?id=" + user_myBatis.getId();
    }

    @GetMapping("/redirect2")
    public ModelAndView redirect2(String userName, String note) {
        User_MyBatis user_myBatis = new User_MyBatis();
        user_myBatis.setUserName(userName);
        user_myBatis.setNote(note);
        myBatisUserService.insertUser(user_myBatis);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/show?id=" + user_myBatis.getId());
        return modelAndView;
    }

    @GetMapping("/redirect3")
    public String redirect3(String userName, String note, RedirectAttributes redirectAttributes) {
        User_MyBatis user_myBatis = new User_MyBatis();
        user_myBatis.setUserName(userName);
        user_myBatis.setNote(note);
        myBatisUserService.insertUser(user_myBatis);

        redirectAttributes.addFlashAttribute("user", user_myBatis);
        return "redirect:/user/show2";
    }

    @GetMapping("/redirect4")
    public ModelAndView redirect4(String userName, String note, RedirectAttributes redirectAttributes) {
        User_MyBatis user_myBatis = new User_MyBatis();
        user_myBatis.setUserName(userName);
        user_myBatis.setNote(note);
        myBatisUserService.insertUser(user_myBatis);

        redirectAttributes.addFlashAttribute("user", user_myBatis);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/show2");
        return modelAndView;
    }

    @GetMapping("/head/page")
    public String headPage() {
        return "head/head";
    }

    @PostMapping("/head/user")
    @ResponseBody
    // @RequestHeader("id")从http请求头中获取信息
    public User_MyBatis headerUser(@RequestHeader("id") Long id) {
        User_MyBatis user_myBatis = myBatisUserService.getUser(id);
        return user_myBatis;
    }

    @GetMapping("/restful")
    public String index() {
        return "restful/index";
    }

    private User changeToPo(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setNote(userVo.getNote());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        return user;
    }

    private UserVo changeToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setNote(user.getNote());
        userVo.setSexCode(user.getSex().getId());
        userVo.setSexName(user.getSex().getName());
        return userVo;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        return userService.insertUser(user);
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public UserVo getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserVo userVo = this.changeToVo(user);
        return userVo;
    }

    // 参数数量必须完全匹配才可以请求成功
    @GetMapping("/user/{userName}/{note}/{start}/{limit}")
    @ResponseBody
    public List<User> getUsers(@PathVariable("userName") String userName, @PathVariable("note") String note,
                               @PathVariable("start") int start, @PathVariable("limit") int limit) {
        List<User> users = userService.getUsers(userName, note, start, limit);
        return users;
    }

    @ResponseBody
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    @ResponseBody
    @PatchMapping("/user/{id}/{userName}")
    public ResultVo updateUserName(@PathVariable("id") Long id, @PathVariable("userName") String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新失败");
        return resultVo;
    }

    @ResponseBody
    @DeleteMapping("/user/{id}")
    public ResultVo deleteUser(@PathVariable("id") Long id) {
        int result = userService.deleteUser(id);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "删除成功" : "删除失败");
        return resultVo;
    }

    @ResponseBody
    @PatchMapping("/user/name")
    public ResultVo updateUserName2(Long id, String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新失败");
        return resultVo;
    }

    @GetMapping("/user/name")
    public String changeUserNamePage() {
        return "restful/change_user_name";
    }

    @ResponseBody
    @GetMapping(value = "/exp/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserVo getUserForExp(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user==null){
            throw new NotFoundException(1L,"未查找到指定用户！");
        }
        UserVo userVo = this.changeToVo(user);
        return userVo;
    }
}
