package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.dao.PersonDao;
import com.guzx.chapter2.pojo.Person;
import com.guzx.chapter2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public Person getPerson(Long id) {
        return personDao.getPerson(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1,propagation = Propagation.NESTED)
    public int insertPerson(Person person) {
        return personDao.insertPerson(person);
    }
}
