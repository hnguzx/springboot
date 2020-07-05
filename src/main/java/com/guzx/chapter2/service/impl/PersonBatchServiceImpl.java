package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.pojo.Person;
import com.guzx.chapter2.service.PersonBatchService;
import com.guzx.chapter2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PersonBatchServiceImpl implements PersonBatchService {
    @Autowired
    private PersonService personService;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertPersons(List<Person> personList) {
        int count = 0;
        for (Person person : personList) {
            count += personService.insertPerson(person);
        }
        return count;
    }
}
