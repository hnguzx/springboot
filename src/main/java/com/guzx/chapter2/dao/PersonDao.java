package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao {
    Person getPerson(Long id);
    int insertPerson(Person person);
}
