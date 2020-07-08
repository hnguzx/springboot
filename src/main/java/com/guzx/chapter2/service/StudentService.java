package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    Student getStudent(long id);

    Student insertStudent(Student student);

    Student updateStudent(Long id,String userName);

    List<Student> findStudents(String userName, String note);

    int deleteStudent(long id);
}
