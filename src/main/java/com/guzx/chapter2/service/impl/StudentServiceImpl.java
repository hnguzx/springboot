package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.dao.StudentDao;
import com.guzx.chapter2.pojo.Student;
import com.guzx.chapter2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    // Cacheable先从缓存中查询，如果查询到结果，直接返回。否则执行方法，返回数据并将数据保存到缓存中
    // 取参数id缓存用户
    @Cacheable(value = "redisCache", key = "'redis_student_'+#id")
    public Student getStudent(long id) {
        return studentDao.getStudent(id);
    }

    @Override
    @Transactional
    // 最后MyBatis会回填id，取结果id缓存用户
    @CachePut(value = "redisCache", key = "'redis_student_'+#result.id")
    public Student insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    @Transactional
    // CachePut将方法结果放入缓存中
    // 更新数据后，更新缓存，如果condition配置项使结果返回为null，则不缓存
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_user_'+#id")
    public Student updateStudent(Long id, String userName) {
        Student student = this.getStudent(id);
        if (student == null) {
            return null;
        }
        student.setUserName(userName);
        studentDao.updateStudent(student);
        return student;
    }

    @Override
    @Transactional
    // 命中率低，不采用缓存
    public List<Student> findStudents(String userName, String note) {
        return studentDao.findStudents(userName, note);
    }

    @Override
    @Transactional
    // CacheEvict通过定义的键移除缓存，beforeInvocation表示是否在方法执行之前移除
    // 移除缓存
    @CacheEvict(value = "redisCache", key = "'redis_student_'+#id", beforeInvocation = false)
    public int deleteStudent(long id) {
        return studentDao.deleteStudent(id);
    }
}
