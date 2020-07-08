package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.Student;
import com.guzx.chapter2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/getStudent")
    @ResponseBody
    public Student getStudent(Long id) {
        return studentService.getStudent(id);
    }

    @RequestMapping("/insertStudent")
    @ResponseBody
    public Student insertStudent(String userName, String note) {
        Student student = new Student();
        student.setUserName(userName);
        student.setNote(note);
        studentService.insertStudent(student);
        return student;
    }

    @RequestMapping("/findStudent")
    @ResponseBody
    public List<Student> findStudents(String userName, String note) {
        return studentService.findStudents(userName, note);
    }

    @RequestMapping("/updateStudent")
    @ResponseBody
    public Map<String, Object> updateStudent(Long id, String userName) {
        Student student = studentService.updateStudent(id, userName);
        boolean flag = student != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/deleteStudent")
    @ResponseBody
    public Map<String, Object> deleteStudent(Long id) {
        int result = studentService.deleteStudent(id);
        Boolean flag = result == 1;
        String message = flag ? "删除成功" : "删除失败";
        return resultMap(flag, message);
    }

    private Map<String, Object> resultMap(boolean success, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}
