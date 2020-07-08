package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    Student getStudent(long id);

    int insertStudent(Student student);

    int updateStudent(Student student);

    List<Student> findStudents(@Param("userName") String userName, @Param("note") String note);

    int deleteStudent(long id);
}
