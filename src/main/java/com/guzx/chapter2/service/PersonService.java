package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.Person;

public interface PersonService {
    public Person getPerson(Long id);
    public int insertPerson(Person person);
}
